package little.old.me.domain.core.service;

import little.old.me.domain.core.model.Settings;
import little.old.me.domain.core.service.SettingsService;
import little.old.me.domain.port.in.SaveSettingsCommand;
import little.old.me.domain.port.out.FetchSettingsPort;
import little.old.me.domain.port.out.SaveSettingsPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SettingsServiceTest {

    @Mock
    private FetchSettingsPort fetchSettingsPort;

    @Mock
    private SaveSettingsPort saveSettingsPort;

    @InjectMocks
    private SettingsService settingsService;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void given_mock_fetchSettingsPort_when_fetchSettings_then_fetchSettingsPort_fetchSettings_called() {
        // Given
        Settings settings = Settings.builder()
                .dataPath("dataPath")
                .build();

        // When
        when(fetchSettingsPort.fetchSettings()).thenReturn(settings);
        Settings result = settingsService.fetchSettings();

        // Then
        verify(fetchSettingsPort, times(1)).fetchSettings();
        assertEquals(settings, result);
    }

    @Test
    void given_valid_command_when_saveSettings_then_saveSettingsPort_saveSettings_called() {
        // Given
        SaveSettingsCommand command = SaveSettingsCommand.builder()
                .dataPath("dataPath")
                .build();

        Settings settings = Settings.builder()
                .dataPath("dataPath_old")
                .build();

        // When
        when(fetchSettingsPort.fetchSettings()).thenReturn(settings);
        settingsService.saveSettings(command);

        // Then
        verify(saveSettingsPort, times(1)).saveSettings(settings);
    }

    @Test
    void given_invalid_command_when_saveSettings_then_throw_exception() {
        // Given
        SaveSettingsCommand command = SaveSettingsCommand.builder()
                .dataPath(null)
                .build();

        // When and Then
        assertThrows(IllegalArgumentException.class, () -> settingsService.saveSettings(command));
    }

    @Test
    void given_null_command_when_saveSettings_then_throw_exception() {
        // Given
        SaveSettingsCommand command = null;

        // When and Then
        assertThrows(IllegalArgumentException.class, () -> settingsService.saveSettings(command));
    }
}
