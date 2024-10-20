package little.old.me.ingestion.domain.core.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import little.old.me.ingestion.domain.core.model.AllianzV1;
import little.old.me.ingestion.domain.core.model.RawData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class PayloadMapperFactory {
    private final Instance<PayloadMapper> payloadMappers;

    public PayloadMapper getPayloadMapper(@NonNull RawData.SourceType source, int version) {
        Class<?> pt = getPayloadType(source, version);
        return payloadMappers.stream()
                .filter(pm -> pm.getType().equals(pt))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No mapper found for: " + source + " v." + version));
    }

    private Class<?> getPayloadType(@NonNull RawData.SourceType source, int version) {
        if (source.equals(RawData.SourceType.allianz)) {
            if (version == 1) {
                return AllianzV1.class;
            }
        }
        throw new IllegalArgumentException("No mapper found for: " + source + " v." + version);
    }
}
