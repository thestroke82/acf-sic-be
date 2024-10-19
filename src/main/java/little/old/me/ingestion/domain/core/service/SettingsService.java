package little.old.me.ingestion.domain.core.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import little.old.me.ingestion.domain.core.model.Settings;
import little.old.me.ingestion.domain.port.in.FetchSettingsUseCase;
import little.old.me.ingestion.domain.port.in.SaveSettingsCommand;
import little.old.me.ingestion.domain.port.in.SaveSettingsUseCase;
import little.old.me.ingestion.domain.port.out.LoadSettingsPort;
import little.old.me.ingestion.domain.port.out.PersistSettingsPort;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SettingsService implements FetchSettingsUseCase, SaveSettingsUseCase {

    private final LoadSettingsPort fetchSettingsPort;
    private final PersistSettingsPort saveSettingsPort;

    @Override
    public Settings fetchSettings() {
        return this.fetchSettingsPort.loadSettings().orElseThrow(
                () -> new IllegalStateException("Settings not found"));
    }


    @Override
    @Transactional
    public void saveSettings(SaveSettingsCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Save Settings command cannot be null");
        }
        if (!command.isValid()) {
            throw new IllegalArgumentException("Save Settings command is not valid");
        }
        Settings settings = fetchSettings();

        settings.setDataPath(command.getDataPath());

        saveSettingsPort.persistSettings(settings);
    }


}
