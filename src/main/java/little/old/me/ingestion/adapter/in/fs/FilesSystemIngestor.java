package little.old.me.ingestion.adapter.in.fs;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.domain.core.model.Settings;
import little.old.me.ingestion.domain.port.in.FetchSettingsUseCase;
import little.old.me.ingestion.domain.port.in.IngestRawDataCommand;
import little.old.me.ingestion.domain.port.in.IngestRawDataUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.context.ManagedExecutor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.Instant;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicReference;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@ApplicationScoped
@Startup
@Slf4j
@RequiredArgsConstructor
public class FilesSystemIngestor {

    private static final String FILE_PREFIX = "acf_sic";
    private static final String FILE_EXTENSION = ".json";


    private final FetchSettingsUseCase fetchSettingsUseCase;

    private final IngestRawDataUseCase ingestRawDataUseCase;

    private final ManagedExecutor managedExecutor;

    @Getter
    private final AtomicReference<WatchService> currentWatchService = new AtomicReference<>();
    private final AtomicReference<Path> currentDirectory = new AtomicReference<>();

    @PostConstruct
    public void onStart() {
        initializeFileWatching();
    }

    // Dynamically restart file watching when settings change
    public synchronized void restartWatching() {
        log.info("Restarting file watching due to settings change...");
        stopWatching();
        initializeFileWatching();
    }

    // Initialize file watching process based on settings
    private synchronized void initializeFileWatching() {
        Settings settings = fetchSettingsUseCase.fetchSettings();
        Path directoryPath = Paths.get(settings.getDataPath());

        if (!Files.isDirectory(directoryPath)) {
            log.error("Invalid data path: {}", directoryPath);
            throw new IllegalArgumentException("Invalid data path");
        }

        // Update current directory reference
        currentDirectory.set(directoryPath);

        // Scan existing files
        scanDirectoryForExistingFiles(directoryPath);

        // Start watching the directory
        startWatchingDirectory(directoryPath);
    }

    // Scans the directory for existing files matching the prefix and extension
    public void scanDirectoryForExistingFiles(Path directoryPath) {
        log.info("Scanning directory {} for existing files", directoryPath);
        try {
            Files.list(directoryPath)
                    .filter(this::isFileRelevant)
                    .forEach(this::processFileWithRetries);
        } catch (IOException e) {
            log.error("Failed to scan directory: {}", directoryPath, e);
        }
    }

    // Start watching the directory using ManagedExecutor
    public void startWatchingDirectory(Path directoryPath) {
        CompletionStage<Void> watchTask = managedExecutor.runAsync(() -> {
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                directoryPath.register(watchService, ENTRY_CREATE);
                currentWatchService.set(watchService); // Keep reference for shutdown

                log.info("Watching directory {} for new files", directoryPath);
                while (!Thread.currentThread().isInterrupted()) {
                    WatchKey key = watchService.take();

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == ENTRY_CREATE) {
                            log.trace("New file detected in directory: {}", directoryPath);
                            WatchEvent<Path> ev = (WatchEvent<Path>) event;
                            Path filePath = directoryPath.resolve(ev.context());
                            if (isFileRelevant(filePath)) {
                                processFileWithRetries(filePath);
                            }
                        }
                    }
                    key.reset();
                }
            } catch (InterruptedException e) {
                log.warn("Directory watch interrupted");
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                log.error("Error watching directory: {}", directoryPath, e.getMessage());
            }
        });

        watchTask.whenComplete((result, error) -> {
            if (error != null) {
                log.error("Directory watching failed", error);
            }
        });
    }

    // Stop watching the current directory and clean up resources
    public synchronized void stopWatching() {
        WatchService watchService = currentWatchService.getAndSet(null);
        if (watchService != null) {
            try {
                watchService.close();
                log.info("Stopped watching directory {}", currentDirectory.get());
            } catch (IOException e) {
                log.error("Failed to stop watching directory", e);
            }
        }
    }

    private void processFileWithRetries(Path filePath) {
        processFileWithRetries(filePath, 3);
    }

    private void processFileWithRetries(Path filePath, int maxRetries) {
        int retryCount = 0;
        boolean success = false;
        FileProcessingLogContext logContext = new FileProcessingLogContext(filePath.getFileName().toString());
//        try{
//            tentativelyWaitForDownload(filePath, 5000);
//        }catch (Exception e){
//            logContext.setOutcome("Failed");
//            logContext.addDetail("File was never ready: " + e.getMessage());
//            // little trick to not enter the retry loop
//            retryCount = maxRetries;
//        }

        while (retryCount < maxRetries && !success) {
            try {
                processFile(filePath, logContext);  // Attempt to process the file once

                logContext.setOutcome("Success");  // If successful, set the outcome

                success = true;  // Mark success if no exception was thrown
            } catch (IOException e) {
                logContext.incrementRetryCount();
                logContext.addDetail(String.format("Retry %d/%d failed: %s.", logContext.getRetryCount(), maxRetries, e.getMessage()));
                log.trace("Stack trace: \n", e);

                retryCount++;
                if (retryCount < maxRetries) {
                    try {
                        Thread.sleep(1000); // Wait for 1 second before retrying
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                        logContext.addDetail("Thread interrupted.");
                        logContext.setOutcome("Failed");
                        break; // Exit the retry loop if the thread is interrupted
                    }
                } else {
                    logContext.setOutcome("Failed");
                    logContext.addDetail("Exceeded maximum retries.");
                }
            } catch (Exception e) {
                logContext.addDetail(String.format("Unexpected error: %s.", e.getMessage()));
                logContext.setOutcome("Failed");
                break;
            }
        }

        // Log the consolidated outcome using logContext.toString()
        log.info(logContext.toString());
    }

    private void processFile(Path filePath, FileProcessingLogContext logContext) throws IOException {

        // Read the file content
        String content = FileUtils.readFileToString(filePath.toFile(), StandardCharsets.UTF_8);
        IngestRawDataCommand command = IngestRawDataCommand.builder()
                .data(content)
                .build();

        // Validate and process the file
        if (command.isValid()) {
            ingestRawDataUseCase.ingestRawData(command);
            logContext.addDetail("Successfully processed.");
        } else {
            logContext.addDetail("Invalid data in file.");
            throw new IOException("Invalid data in file");
        }

        // Attempt to delete the file (best-effort)
        try {
            safeDelete(filePath);
            logContext.addDetail("File deleted.");
        } catch (Exception e) {
            logContext.addDetail("File deletion failed: " + e.getMessage());
        }
    }

    private void tentativelyWaitForDownload(Path filePath, int maxMsWait) throws Exception{
        Instant start = Instant.now();
        int waitTimeMs = (int) Math.ceil(maxMsWait / 20.0);
        int lastSize = 0;
        int currentSize = -1;

        while( currentSize != lastSize && Instant.now().isBefore(start.plusMillis(maxMsWait))) {
            lastSize = currentSize;
            currentSize = (int) Files.size(filePath);
            safeSleep(waitTimeMs);
        }

        if(currentSize != lastSize){
            throw new Exception("Max wait time exceeded.");
        }
    }


    // Check if the file matches the prefix and extension
    private boolean isFileRelevant(Path filePath) {
        String fileName = filePath.getFileName().toString();
        boolean isInteresting = fileName.startsWith(FILE_PREFIX) && fileName.endsWith(FILE_EXTENSION);
        if (!isInteresting) {
            return false;
        }
        boolean ret = false;
        try{
            tentativelyWaitForDownload(filePath, 5000);
            ret = Files.exists(filePath);
        }finally {
            return ret;
        }
    }

    private void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void safeDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            log.debug("Failed to delete file: {}  => {}", path, e.getMessage());
        }
    }

}
