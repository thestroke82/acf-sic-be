package little.old.me.ingestion.adapter.out.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import little.old.me.shared.adapter.persistance.entity.AuditedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity(name = "raw_data")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RawDataEntity extends AuditedEntity {

    @Enumerated(EnumType.STRING)
    private SourceType source;

    private int version;
    private String url;
    private Instant timestamp;

    private String payload;

    public enum SourceType {
        allianz
    }
}
