package little.old.me.ingestion.adapter.out.persistance;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.RawDataEntity;
import little.old.me.ingestion.adapter.out.persistance.mapper.RawDataEntityMapper;
import little.old.me.ingestion.adapter.out.persistance.mapper.RawDataMapper;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.ingestion.domain.port.out.LoadRawDataPort;
import little.old.me.ingestion.domain.port.out.PersistRawDataPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class RawDataEntityRepository implements PanacheRepository<RawDataEntity>,
        PersistRawDataPort, LoadRawDataPort {
    private final RawDataEntityMapper rawDataEntityMapper;
    private final RawDataMapper rawDataMapper;
    @Override
    public long persistRawData(@NonNull RawData rawData) {
        RawDataEntity rawDataEntity = rawDataEntityMapper.map(rawData);
        rawDataEntity.persist();
        return rawDataEntity.getId();
    }


    @Override
    public Optional<RawData> loadByPayloadChecksum(byte[] checksum) {
       return find("payloadChecksum", checksum).firstResultOptional()
               .map(r -> rawDataMapper.map(r));
    }
}
