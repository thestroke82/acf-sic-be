package little.old.me.shared.domain.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCertificate {

    private String length;
    private LocalDate date;
    private LocalDate issueDate;
    private String issueType;
    private String description;
    private String usageCategory;
    private String type;
    private String bodywork;
    private String plate;
    private String owner;
    private String residenceProvince;
    private String residenceMunicipality;
    private String residenceMunicipalityIstatCode;
    private String curbWeight;
    private String maxLoadWeight;
    private String maxTowableWeight;
}
