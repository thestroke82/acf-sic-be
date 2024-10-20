package little.old.me.ingestion.domain.core.mapper;

import little.old.me.ingestion.domain.core.model.RawData;

import java.util.Optional;

public interface PayloadMapper<T> {
    Optional<T> map(String payload);
    Class<T> getType();
}
