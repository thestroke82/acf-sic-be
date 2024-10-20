package little.old.me.ingestion.domain.core.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import little.old.me.ingestion.domain.core.mapper.RawDataMapper;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.ingestion.domain.port.in.IngestRawDataCommand;
import little.old.me.ingestion.domain.port.in.IngestRawDataUseCase;
import little.old.me.ingestion.domain.port.out.LoadRawDataPort;
import little.old.me.ingestion.domain.port.out.PersistRawDataPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class RawDataService implements IngestRawDataUseCase {

    private final RawDataMapper rawDataMapper;
    // private final PayloadMapperFactory payloadMapperFactory;
    private final PersistRawDataPort persistRawDataPort;
    private final LoadRawDataPort loadRawDataPort;

    @Override
    @Transactional
    public void ingestRawData(IngestRawDataCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Ingest Raw Data command cannot be null");
        }
        if (!command.isValid()) {
            throw new IllegalArgumentException("Ingest Raw Data command is not valid");
        }

        RawData rawData = rawDataMapper.map(command.getData());

        // only continue if the raw data is not already persisted
        if (loadRawDataPort.loadByPayloadChecksum(rawData.getPayloadChecksum()).isPresent()) {
            log.info("Raw data already persisted: {}", rawData);
            return;
        }

//        PayloadMapper payloadMapper = payloadMapperFactory
//                .getPayloadMapper(rawDataString.getSource(), rawDataString.getVersion());
//        Optional<?> payload = payloadMapper.map(rawDataString.getPayload());
//        if (!payload.isPresent()) {
//            log.error("Failed to serialize payload: {}", rawDataString);
//            return;
//        }
        persistRawDataPort.persistRawData(rawData);

    }
}
