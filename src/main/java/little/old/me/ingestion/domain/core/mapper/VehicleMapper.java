package little.old.me.ingestion.domain.core.mapper;

import little.old.me.shared.domain.core.model.Vehicle;
import little.old.me.shared.mapper.Mapper;

public interface VehicleMapper<S> extends Mapper <S, Vehicle> {
    Class<S> getSourceType();
}
