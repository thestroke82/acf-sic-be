package little.old.me.ingestion.domain.core.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.shared.exception.MappingException;
import little.old.me.shared.infra.RegisterCustomModulesCustomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RawDataMapperTest {

    private final String goodJsonString = "{\"source\":\"allianz\",\"version\":\"1\",\"url\":\"https://portaleagenzie.allianz.it/motor/inquiry/ania-bff/ricerca/getRicercaPerTarga/FE554YK?sigla=\",\"timestamp\":\"2024-10-17T16:42:24.238Z\",\"payload\":{\"ciccia\":\"ar sugo\"}}";
    private final String badJsonString = "{\"source\":\"WRONG\",\"version\":\"1\",\"url\":\"https://portaleagenzie.allianz.it/motor/inquiry/ania-bff/ricerca/getRicercaPerTarga/FE554YK?sigla=\",\"timestamp\":\"2024-10-17T16:42:24.238Z\",\"payload\":{\"ciccia\":\"ar sugo\"}}";


    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private RawDataMapper rawDataMapper;

    @BeforeEach
    void setUp() {
        new RegisterCustomModulesCustomizer().customize(objectMapper);
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_goodJsonString_when_map_then_return_right_rawData() {
        // when
        RawData rawData = rawDataMapper.map(goodJsonString);

        // then
        assertEquals(RawData.SourceType.allianz, rawData.getSource());
        assertEquals(1, rawData.getVersion());
        assertEquals("https://portaleagenzie.allianz.it/motor/inquiry/ania-bff/ricerca/getRicercaPerTarga/FE554YK?sigla=", rawData.getUrl());
        assertEquals("2024-10-17T16:42:24.238Z", rawData.getTimestamp().toString());

        assertEquals("{\"ciccia\":\"ar sugo\"}", rawData.getPayload());
    }

    @Test
    void given_badJsonString_when_map_then_throw_exception() {
        // when
        assertThrows(MappingException.class, () -> rawDataMapper.map(badJsonString));
    }

}