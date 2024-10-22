package little.old.me.ingestion.adapter.out.persistance.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.RawDataEntity;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.shared.exception.MappingException;
import little.old.me.shared.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@ApplicationScoped
@RequiredArgsConstructor
public class RawDataEntityMapper implements Mapper<RawData, RawDataEntity> {
    private final ModelMapper modelMapper;

    @Override
    public RawDataEntity map(RawData input) {
        try {
            RawDataEntity output = modelMapper.map(input, RawDataEntity.class);
            if (input.getId() != null) {
                output.setId(input.getId().getValue());
            }
            return output;
        } catch (Exception e) {
            throw new MappingException("Error while mapping to RawDataEntity", e);
        }
    }
}
