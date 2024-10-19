package little.old.me.ingestion.domain.core;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import little.old.me.ingestion.domain.core.model.Settings;
import little.old.me.ingestion.domain.port.out.LoadSettingsPort;
import little.old.me.ingestion.domain.port.out.PersistSettingsPort;
import little.old.me.ingestion.infra.SystemSupport;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class AppLifecycleBean {

    private final LoadSettingsPort fetchSettingsPort;
    private final PersistSettingsPort saveSettingsPort;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        initializeDefaultSettings();
    }


    private void initializeDefaultSettings() {
        if (fetchSettingsPort.loadSettings().isEmpty()) {
            saveSettingsPort.persistSettings(createDefaultSettings());
        }
    }

    private Settings createDefaultSettings() {
        return Settings.builder()
                .dataPath(SystemSupport.getDownloadsDirectory())
                .build();
    }
}
