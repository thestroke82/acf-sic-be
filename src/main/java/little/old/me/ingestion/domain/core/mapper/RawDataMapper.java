package little.old.me.ingestion.domain.core.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.domain.core.model.RawData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class RawDataMapper {

    private final ObjectMapper objectMapper;

    public Optional<RawData> map(@NonNull String rawData) {
        try {
            // Parse everything except payload
            JsonNode rootNode = objectMapper.readTree(rawData);

            // Deserialize other fields into RawData object
            RawData result = objectMapper.treeToValue(rootNode, RawData.class);

            // Manually handle the payload as a string
            Optional.ofNullable(rootNode.get("payload"))
                    .ifPresent(node -> result.setPayload(node.toString()));

            return Optional.of(result);
        } catch (JsonProcessingException e) {
            log.error("Error while mapping raw data: {}", e.getMessage());
            log.trace("", e);
            return Optional.empty();
        }
    }
}
