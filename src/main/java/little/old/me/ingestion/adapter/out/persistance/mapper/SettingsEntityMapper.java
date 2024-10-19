package little.old.me.ingestion.adapter.out.persistance.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.SettingsEntity;
import little.old.me.ingestion.domain.core.model.Settings;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@ApplicationScoped
@RequiredArgsConstructor
public class SettingsEntityMapper {
    private final ModelMapper modelMapper;

    public SettingsEntity map(Settings input) {
        SettingsEntity output = modelMapper.map(input, SettingsEntity.class);
        return output;
    }
}
