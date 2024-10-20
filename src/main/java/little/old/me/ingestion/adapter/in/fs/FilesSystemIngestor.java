package little.old.me.ingestion.adapter.in.fs;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.domain.port.in.FetchSettingsUseCase;
import little.old.me.ingestion.domain.port.in.IngestRawDataUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class FilesSystemIngestor {
    private final FetchSettingsUseCase fetchSettingsUseCase;
    private final IngestRawDataUseCase ingestRawDataUseCase;

}
