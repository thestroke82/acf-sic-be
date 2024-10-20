package little.old.me.ingestion.e2e;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.transaction.Transactional;
import little.old.me.ingestion.adapter.out.persistance.entity.RawDataEntity;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.ingestion.domain.core.service.RawDataService;
import little.old.me.ingestion.domain.port.in.IngestRawDataCommand;
import little.old.me.ingestion.domain.port.out.PersistRawDataPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import jakarta.inject.Inject;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class RawDataServiceEndToEndTest {

    private final String goodRawDataJson = "{\"source\":\"allianz\",\"version\":\"1\",\"url\":\"https://example.com\",\"timestamp\":\"2024-10-17T16:42:24.238Z\",\"payload\":{\"ciccia\":\"ar sugo\"}}";

    @Inject
    private RawDataService rawDataService;

    @InjectSpy
    private PersistRawDataPort persistRawDataPort;


    @BeforeEach
    void setup() {
        // If needed, you can reset or prepare anything before each test
    }

    @Test
    @Transactional
    public void given_validCommand_when_ingestRawData_then_dataIsPersisted() {
        // Prepare a valid command
        IngestRawDataCommand command = IngestRawDataCommand.builder()
                .data(goodRawDataJson)
                .build();

        AtomicLong persistedId = new AtomicLong();

        // spy the persist raw data port to capture the id of the persisted data
        doAnswer(invocation -> {
            persistedId.set((long) invocation.callRealMethod());
            return persistedId.get();
        }).when(persistRawDataPort).persistRawData(any());


        // Call the service method
        rawDataService.ingestRawData(command);

        // Verify the data is persisted
        verify(persistRawDataPort, times(1)).persistRawData(any());

        // Verify the id of the persisted data
        assert persistedId.get() > 0;

        // load the persisted data and verify the content
        RawDataEntity persistedData = RawDataEntity.findById(persistedId.get());
        assert persistedData != null;
        assertEquals(1, persistedData.getVersion());
        assertEquals("{\"ciccia\":\"ar sugo\"}", persistedData.getPayload());
    }
}
