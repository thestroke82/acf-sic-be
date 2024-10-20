package little.old.me.ingestion.adapter.in.fs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class FileProcessingLogContext {
    private final String fileName;
    private String outcome = "Processing";  // Default outcome
    private int retryCount = 0;
    private final StringBuilder details = new StringBuilder();

    public FileProcessingLogContext(String fileName) {
        this.fileName = fileName;
    }

    public void incrementRetryCount() {
        retryCount++;
    }

    public void addDetail(String detail) {
        details.append(detail).append(" ");
    }

    @Override
    public String toString() {
        return String.format("File: %s | Outcome: %s | Retries: %d | Details: %s", 
                             fileName, outcome, retryCount, details.toString().trim());
    }
}