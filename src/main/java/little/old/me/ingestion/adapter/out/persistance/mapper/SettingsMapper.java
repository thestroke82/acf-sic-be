package little.old.me.ingestion.adapter.out.persistance.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.SettingsEntity;
import little.old.me.ingestion.domain.core.model.Settings;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@ApplicationScoped
@RequiredArgsConstructor
public class SettingsMapper {
    private final ModelMapper modelMapper;


    public Settings map(@NonNull  SettingsEntity input) {
        Settings output = modelMapper.map(input, Settings.class);
        return output;
    }
}
