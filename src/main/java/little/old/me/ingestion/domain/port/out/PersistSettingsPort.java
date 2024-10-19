package little.old.me.ingestion.domain.port.out;

import little.old.me.ingestion.domain.core.model.Settings;

public interface PersistSettingsPort {
    void persistSettings(Settings settings);
}
