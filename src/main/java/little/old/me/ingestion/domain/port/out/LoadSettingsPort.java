package little.old.me.ingestion.domain.port.out;

import little.old.me.ingestion.domain.core.model.Settings;

import java.util.Optional;

public interface LoadSettingsPort {
    Optional<Settings> loadSettings();
}
