package little.old.me.ingestion.adapter.out.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import little.old.me.shared.adapter.persistance.entity.AuditedEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "settings")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingsEntity extends AuditedEntityBase {
    public static final long ONLY_ID = 1L;

    @Id
    @Builder.Default
    public Long id = ONLY_ID;
    public String dataPath;
}
