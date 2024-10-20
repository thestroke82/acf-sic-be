package little.old.me.ingestion.adapter.in.rest;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import little.old.me.ingestion.domain.core.model.Settings;
import little.old.me.ingestion.domain.port.in.FetchSettingsUseCase;
import little.old.me.ingestion.domain.port.in.SaveSettingsCommand;
import little.old.me.ingestion.domain.port.in.SaveSettingsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/settings")
@Slf4j
@RequiredArgsConstructor
public class SettingsController {

    private final FetchSettingsUseCase fetchSettingsUseCase;
    private final SaveSettingsUseCase saveSettingsUseCase;


    @GET
    public Settings getSettings() {
        return fetchSettingsUseCase.fetchSettings();
    }

    @PUT
    public void saveSettings(SaveSettingsCommand saveSettingsCommand) {
        saveSettingsUseCase.saveSettings(saveSettingsCommand);
    }

}
