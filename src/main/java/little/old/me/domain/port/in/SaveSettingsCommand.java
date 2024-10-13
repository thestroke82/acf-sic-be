package little.old.me.domain.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class SaveSettingsCommand {
    private String dataPath;


    public boolean isValid() {
        return StringUtils.isNotEmpty(dataPath);
    }
}
