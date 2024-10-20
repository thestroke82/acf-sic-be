package little.old.me.ingestion.domain.core.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.All;
import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.domain.core.model.AllianzV1;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class AllianzV1Mapper implements PayloadMapper<AllianzV1>{

    private final ObjectMapper objectMapper;

    @Override
    public Optional<AllianzV1> map(@NonNull String payload) {
        try {
            AllianzV1 result = objectMapper.readValue(payload, AllianzV1.class);
            return Optional.of(result);
        } catch (Exception e) {
            log.error("Error while mapping raw data: {}", e.getMessage());
            log.trace("",e);
            return Optional.empty();
        }
    }

    @Override
    public Class<AllianzV1> getType() {
        return AllianzV1.class;
    }


}
