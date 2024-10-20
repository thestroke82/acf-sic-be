package little.old.me.ingestion.adapter.out.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY for SQLite
    private Long id;

    @Enumerated(EnumType.STRING)
    private SourceType source;

    private int version;
    private String url;
    private Instant timestamp;

    private String payload;
    private byte[] payloadChecksum;

    public enum SourceType {
        allianz
    }
}
