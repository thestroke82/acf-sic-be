package little.old.me.shared.adapter.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "vehicle")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity extends AuditedEntity {

    @Column(nullable = false)
    private String plate;
    private String owner;
    private LocalDateTime insuranceCoverageEnd;

    @Column(columnDefinition = "JSON")
    private String registrations;

    @Column(columnDefinition = "JSON")
    private String insurancePolicies;

    @Column(columnDefinition = "JSON")
    private String riskCertificates;

    private String rawDataIds; // comma separated list of IDs
}
