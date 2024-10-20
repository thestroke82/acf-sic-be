package little.old.me.ingestion.adapter.out.persistance.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import little.old.me.ingestion.adapter.out.persistance.entity.RawDataEntity;
import little.old.me.ingestion.domain.core.model.RawData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RawDataMapperTest {
    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private RawDataMapper rawDataMapper;

    @BeforeEach
    public void setup() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_valid_rawDataEntity_then_map_correctly() {
        // given
        RawDataEntity rawDataEntity = RawDataEntity.builder()
                .id(1l)
                .source(RawDataEntity.SourceType.allianz)
                .version(1)
                .url("www.gogle.com")
                .timestamp(Instant.parse("2024-10-17T16:42:24.238Z"))
                .payload("{\"fabio\":\"bello\"}")
                .build();

        // when
        RawData rawData = rawDataMapper.map(rawDataEntity);

        // then
        assertEquals(RawData.SourceType.allianz, rawData.getSource());
        assertEquals(1, rawData.getVersion());
        assertEquals("www.gogle.com", rawData.getUrl());
        assertEquals("2024-10-17T16:42:24.238Z", rawData.getTimestamp().toString());
        assertEquals("{\"fabio\":\"bello\"}", rawData.getPayload());
    }


}