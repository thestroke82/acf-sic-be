package little.old.me.shared.domain.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimsRecord {
    private int year;

    private ClaimValue main;
    private ClaimValue mainDamagedProperty;
    private ClaimValue mainInjuredPeople;
    private ClaimValue mainMixed;

    private ClaimValue equal;
    private ClaimValue equalDamagedProperty;
    private ClaimValue equalInjuredPeople;
    private ClaimValue equalMixed;
}
