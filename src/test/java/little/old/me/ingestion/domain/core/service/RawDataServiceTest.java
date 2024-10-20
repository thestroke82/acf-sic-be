package little.old.me.ingestion.domain.core.service;

import little.old.me.ingestion.domain.core.mapper.RawDataMapper;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.ingestion.domain.port.in.IngestRawDataCommand;
import little.old.me.ingestion.domain.port.out.PersistRawDataPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RawDataServiceTest {

    @Mock
    private RawDataMapper rawDataMapper;

    @Mock
    private PersistRawDataPort persistRawDataPort;

    @InjectMocks
    private RawDataService rawDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_nullCommand_when_ingestRawData_then_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rawDataService.ingestRawData(null));
    }

    @Test
    void given_invalidCommand_when_ingestRawData_then_throwIllegalArgumentException() {
        IngestRawDataCommand command = mock(IngestRawDataCommand.class);
        when(command.isValid()).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> rawDataService.ingestRawData(command));
    }

    @Test
    void given_mapperFails_when_ingestRawData_then_logError() {
        IngestRawDataCommand command = mock(IngestRawDataCommand.class);
        when(command.isValid()).thenReturn(true);
        when(command.getData()).thenReturn("raw data string");

        when(rawDataMapper.map(any())).thenReturn(Optional.empty());

        rawDataService.ingestRawData(command);

        verify(rawDataMapper).map(any());
        verify(persistRawDataPort, never()).persistRawData(any());
    }

    @Test
    void given_mapperSucceeds_when_ingestRawData_then_persistRawDataIsCalled() {
        IngestRawDataCommand command = mock(IngestRawDataCommand.class);
        when(command.isValid()).thenReturn(true);
        when(command.getData()).thenReturn("raw data string");

        RawData rawData = mock(RawData.class);
        when(rawDataMapper.map(any())).thenReturn(Optional.of(rawData));

        rawDataService.ingestRawData(command);

        verify(rawDataMapper).map(any());
        verify(persistRawDataPort).persistRawData(rawData);
    }
}
