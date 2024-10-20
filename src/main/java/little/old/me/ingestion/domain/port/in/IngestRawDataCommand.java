package little.old.me.ingestion.domain.port.in;


import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Builder
@Getter
public class IngestRawDataCommand {
    private String data;

    public boolean isValid() {
        return StringUtils.isNotEmpty(data);
    }
}
