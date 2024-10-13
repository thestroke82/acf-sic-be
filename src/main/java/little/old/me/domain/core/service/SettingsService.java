package little.old.me.domain.core.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import little.old.me.domain.core.model.Settings;
import little.old.me.domain.port.in.FetchSettingsUseCase;
import little.old.me.domain.port.in.SaveSettingsCommand;
import little.old.me.domain.port.in.SaveSettingsUseCase;
import little.old.me.domain.port.out.FetchSettingsPort;
import little.old.me.domain.port.out.SaveSettingsPort;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SettingsService implements FetchSettingsUseCase, SaveSettingsUseCase {

    private final FetchSettingsPort fetchSettingsPort;
    private final SaveSettingsPort saveSettingsPort;

    @Override
    public Settings fetchSettings() {
        return this.fetchSettingsPort.fetchSettings();
    }


    @Override
    @Transactional
    public void saveSettings(SaveSettingsCommand command) {
        if(command == null) {
            throw new IllegalArgumentException("Save Settings command cannot be null");
        }
        if(!command.isValid()){
            throw new IllegalArgumentException("Save Settings command is not valid");
        }
        Settings settings = fetchSettings();

        settings.setDataPath(command.getDataPath());

        saveSettingsPort.saveSettings(settings);
    }





}
