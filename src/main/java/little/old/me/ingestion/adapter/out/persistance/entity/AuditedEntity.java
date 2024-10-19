package little.old.me.ingestion.adapter.out.persistance.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AuditedEntity extends PanacheEntity {

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant creationDate;

    @UpdateTimestamp
    private Instant lastUpdatedDate;

    private String createdBy;

    private String lastUpdatedBy;
}
