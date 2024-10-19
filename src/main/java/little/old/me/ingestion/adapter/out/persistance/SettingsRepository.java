package little.old.me.ingestion.adapter.out.persistance;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.adapter.out.persistance.entity.SettingsEntity;
import little.old.me.ingestion.adapter.out.persistance.mapper.SettingsEntityMapper;
import little.old.me.ingestion.adapter.out.persistance.mapper.SettingsMapper;
import little.old.me.ingestion.domain.core.model.Settings;
import little.old.me.ingestion.domain.port.out.LoadSettingsPort;
import little.old.me.ingestion.domain.port.out.PersistSettingsPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class SettingsRepository implements LoadSettingsPort, PersistSettingsPort {
    private final SettingsMapper settingsMapper;
    private final SettingsEntityMapper settingsEntityMapper;

    @Override
    public Optional<Settings> loadSettings() {
        return Optional.ofNullable(SettingsEntity.findById(SettingsEntity.ONLY_ID))
                .map(s -> settingsMapper.map((SettingsEntity) s));
    }

    @Override
    public void persistSettings(@NonNull Settings settings) {
        SettingsEntity savedSettings = SettingsEntity.findById(SettingsEntity.ONLY_ID);
        if(savedSettings!=null){
            savedSettings.delete();
        }
        SettingsEntity settingsEntity = this.settingsEntityMapper.map(settings);
        settingsEntity.setId(SettingsEntity.ONLY_ID);
        settingsEntity.persist();
        settingsEntity.flush();
    }
}
