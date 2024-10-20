package little.old.me.ingestion.domain.core.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import little.old.me.ingestion.domain.core.model.RawData;
import little.old.me.shared.exception.MappingException;
import little.old.me.shared.mapper.Mapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class RawDataMapper implements Mapper<String, RawData> {

    private final ObjectMapper objectMapper;

    @Override
    public RawData map(String rawData) {
        try {
            // Parse everything except payload
            JsonNode rootNode = objectMapper.readTree(rawData);

            // Deserialize other fields into RawData object
            RawData result = objectMapper.treeToValue(rootNode, RawData.class);

            // Manually handle the payload as a string
            Optional.ofNullable(rootNode.get("payload"))
                    .ifPresent(node -> result.setPayload(node.toString()));

            return result;
        } catch (Exception e) {
            throw new MappingException("Error while mapping to RawData", e);
        }
    }
}
