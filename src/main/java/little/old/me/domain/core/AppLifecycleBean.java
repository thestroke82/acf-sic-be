package little.old.me.domain.core;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import little.old.me.domain.core.model.Settings;
import little.old.me.domain.core.service.SettingsService;
import little.old.me.domain.port.out.FetchSettingsPort;
import little.old.me.domain.port.out.SaveSettingsPort;
import little.old.me.infra.SystemSupport;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AppLifecycleBean {

    private final FetchSettingsPort fetchSettingsPort;
    private final SaveSettingsPort saveSettingsPort;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        initializeDefaultSettings();
    }


    private void initializeDefaultSettings() {
        Settings settings = fetchSettingsPort.fetchSettings();
        if (settings == null) {
            settings = createDefaultSettings();
            saveSettingsPort.saveSettings(settings);
        }
    }

    private Settings createDefaultSettings() {
        return Settings.builder()
                .dataPath(SystemSupport.getDownloadsDirectory())
                .build();
    }
}
