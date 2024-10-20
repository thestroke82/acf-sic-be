package little.old.me.ingestion.adapter.out.persistance.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.RawDataEntity;
import little.old.me.ingestion.domain.core.model.RawData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class RawDataEntityMapper {
    private final ModelMapper modelMapper;

    public RawDataEntity map(@NonNull RawData input) {
        return modelMapper.map(input, RawDataEntity.class);
    }
}
