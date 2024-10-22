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
public class RawDataMapper implements Mapper<RawDataEntity, RawData> {

    private final ModelMapper modelMapper;

    public RawData map(RawDataEntity input) {
        try {
            RawData output = modelMapper.map(input, RawData.class);
            if (input.getId() != null) {
                output.setId(new RawData.RawDataId(input.getId()));
            }
            return output;
        } catch (Exception e) {
            throw new MappingException("Error while mapping to RawData", e);
        }
    }
}
