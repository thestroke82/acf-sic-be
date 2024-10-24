package little.old.me.ingestion.domain.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawData {
    private RawDataId id;
    private SourceType source;

    private int version;
    private String url;
    private Instant timestamp;

    @JsonIgnore
    private String payload;

    @JsonIgnore
    private byte[] payloadChecksum;

    public enum SourceType {
        allianz
    }

    @AllArgsConstructor
    @Getter
    public static class RawDataId{
        private Integer value;
    }
}
