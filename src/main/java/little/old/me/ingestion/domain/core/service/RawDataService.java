package little.old.me.ingestion.domain.core.service;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.domain.core.mapper.PayloadMapper;
import little.old.me.ingestion.domain.core.mapper.PayloadMapperFactory;
import little.old.me.ingestion.domain.core.mapper.RawDataMapper;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.ingestion.domain.port.in.IngestRawDataCommand;
import little.old.me.ingestion.domain.port.in.IngestRawDataUseCase;
import little.old.me.ingestion.domain.port.out.PersistRawDataPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class RawDataService implements IngestRawDataUseCase {
    private final RawDataMapper rawDataMapper;
    // private final PayloadMapperFactory payloadMapperFactory;
    private final PersistRawDataPort persistRawDataPort;

    @Override
    public void ingestRawData(IngestRawDataCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Ingest Raw Data command cannot be null");
        }
        if (!command.isValid()) {
            throw new IllegalArgumentException("Ingest Raw Data command is not valid");
        }

        Optional<RawData> opt = rawDataMapper.map(command.getData());
        if (!opt.isPresent()) {
            log.error("Failed to serialize raw data: {}", command.getData());
            return;
        }

        RawData rawData = opt.get();
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