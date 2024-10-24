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
public class RiskCertificate {
    private String company;
    private String vehicleType;
    private String format2015;
    private String unpaidDeductibles;
    private String previousClass;
    private String previousClassCU;
    private String policyNumber;

    @Singular
    private List<ClaimsRecord> claimsRecords;
}