package little.old.me.shared.adapter.persistance.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
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
public abstract class AuditedEntity extends PanacheEntityBase {

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant creationDate;

    @UpdateTimestamp
    private Instant lastUpdatedDate;

    private String createdBy;

    private String lastUpdatedBy;
}
