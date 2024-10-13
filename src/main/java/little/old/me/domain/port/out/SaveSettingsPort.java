package little.old.me.domain.port.out;

import little.old.me.domain.core.model.Settings;

public interface SaveSettingsPort {
    void saveSettings(Settings settings);
}
