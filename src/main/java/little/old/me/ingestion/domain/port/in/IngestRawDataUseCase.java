package little.old.me.ingestion.domain.port.in;

public interface IngestRawDataUseCase {
    void ingestRawData(IngestRawDataCommand command);
}
