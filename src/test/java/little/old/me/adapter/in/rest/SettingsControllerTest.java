package little.old.me.adapter.in.rest;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import little.old.me.domain.core.model.Settings;
import little.old.me.domain.port.in.FetchSettingsUseCase;
import little.old.me.domain.port.in.SaveSettingsCommand;
import little.old.me.domain.port.in.SaveSettingsUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SettingsControllerTest {

    @Mock
    private FetchSettingsUseCase fetchSettingsUseCase;

    @Mock
    private SaveSettingsUseCase saveSettingsUseCase;

    @InjectMocks
    private SettingsController settingsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_getSettings_then_invoke_use_case() {
        when(fetchSettingsUseCase.fetchSettings()).thenReturn(Settings.builder().build());
        settingsController.getSettings();
        verify(fetchSettingsUseCase, times(1)).fetchSettings();
    }

    @Test
    void when_saveSettings_then_invoke_use_case() {
        SaveSettingsCommand command = SaveSettingsCommand.builder().build();
        settingsController.saveSettings(command);
        verify(saveSettingsUseCase, times(1)).saveSettings(command);
    }
}