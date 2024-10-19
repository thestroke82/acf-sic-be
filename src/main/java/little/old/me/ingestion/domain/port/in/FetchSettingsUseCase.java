package little.old.me.ingestion.domain.port.in;

import little.old.me.ingestion.domain.core.model.Settings;

public interface FetchSettingsUseCase {
    Settings fetchSettings();
}
