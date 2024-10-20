package little.old.me.ingestion.adapter.out.persistance.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.SettingsEntity;
import little.old.me.ingestion.domain.core.model.Settings;
import little.old.me.shared.exception.MappingException;
import little.old.me.shared.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@ApplicationScoped
@RequiredArgsConstructor
public class SettingsMapper implements Mapper<SettingsEntity, Settings> {
    private final ModelMapper modelMapper;


    @Override
    public Settings map(SettingsEntity input) {
        try {
            return modelMapper.map(input, Settings.class);
        } catch (Exception e) {
            throw new MappingException("Error while mapping to Settings", e);
        }
    }
}
