package little.old.me.shared.domain.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicy {

    private String company;
    private String contractor;
    private LocalDateTime effectiveDate;
    private LocalDateTime coverageEndDate;
    private LocalDate contractEndDate;
    private Boolean tacitRenewal;
    private String policyNumber;
    private String tariff;
    private Boolean blackBox;
    private String policyType;
}