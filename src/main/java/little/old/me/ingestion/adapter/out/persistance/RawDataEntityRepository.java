package little.old.me.ingestion.adapter.out.persistance;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.RawDataEntity;
import little.old.me.ingestion.adapter.out.persistance.mapper.RawDataEntityMapper;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.ingestion.domain.port.out.PersistRawDataPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class RawDataEntityRepository implements PersistRawDataPort {
    private final RawDataEntityMapper rawDataEntityMapper;
    @Override
    public long persistRawData(@NonNull RawData rawData) {
        RawDataEntity rawDataEntity = rawDataEntityMapper.map(rawData);
        rawDataEntity.persist();
        return rawDataEntity.id;
    }
}
