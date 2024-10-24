package little.old.me.shared.domain.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String plate;
    private String owner;

    @Singular
    private List<RegistrationCertificate> registrations;
    @Singular
    private List<InsurancePolicy> insurancePolicies;
    @Singular
    private List<RiskCertificate> riskCertificates;

}
