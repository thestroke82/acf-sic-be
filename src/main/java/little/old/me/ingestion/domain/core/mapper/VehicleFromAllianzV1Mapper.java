package little.old.me.ingestion.domain.core.mapper;

import little.old.me.ingestion.domain.core.model.AllianzV1;
import little.old.me.shared.domain.core.model.RegistrationCertificate;
import little.old.me.shared.domain.core.model.Vehicle;
import little.old.me.shared.support.DateParsingSupport;

public class VehicleFromAllianzV1Mapper implements VehicleMapper<AllianzV1> {
    @Override
    public Class<AllianzV1> getSourceType() {
        return AllianzV1.class;
    }

    @Override
    public Vehicle map(AllianzV1 input) {
        if (input == null) {
            return null;
        }
        Vehicle ret = new Vehicle();

        if (input.getDatiImmatricolazioni() != null) {
            ret.setRegistrations(
                    input.getDatiImmatricolazioni().stream()
                            .map(this::map)
                            .toList()
            );
        }


        return ret;
    }

    private RegistrationCertificate map(AllianzV1.DatiImmatricolazioni input) {
        if (input == null) {
            return null;
        }
        RegistrationCertificate ret = new RegistrationCertificate();
        ret.setLength(input.getLunghezza());
        ret.setDate(DateParsingSupport.parseLocalDate(input.getData()).orElse(null));
        ret.setIssueDate(DateParsingSupport.parseLocalDate(input.getDataEmissione()).orElse(null));
        ret.setIssueType(input.getTipoEmissione());
        ret.setDescription(input.getDescrizione());
        ret.setUsageCategory(input.getCategoriaUso());
        ret.setType(input.getTipo());
        ret.setBodywork(input.getOmologazione());
        ret.setOwner(input.getCodiceFiscaleIntestatario());
        ret.setPlate(input.getTargaTelaio());
        ret.setResidenceProvince(input.getProvincia());
        return ret;
    }
}
