package little.old.me.adapter.out.persistance;

import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.domain.core.model.Settings;
import little.old.me.domain.port.out.FetchSettingsPort;
import little.old.me.domain.port.out.SaveSettingsPort;

@ApplicationScoped
public class SettingsRepository implements FetchSettingsPort, SaveSettingsPort {

    @Override
    public Settings fetchSettings() {
        return Settings.findById(Settings.ONLY_ID);
    }

    @Override
    public void saveSettings(Settings settings) {
        if(settings == null || settings.getId() == null || settings.getId() != Settings.ONLY_ID) {
            throw new IllegalArgumentException("Settings cannot be null and must have ID 1");
        }
        settings.persist();
    }
}
