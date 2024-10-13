package little.old.me.domain.core.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Settings extends PanacheEntityBase {
    public static final long ONLY_ID = 1L;

    @Id
    @Builder.Default
    public Long id = ONLY_ID;
    public String dataPath;

}
