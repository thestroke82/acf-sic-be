package little.old.me.ingestion.domain.port.out;

import little.old.me.ingestion.domain.core.model.RawData;

public interface PersistRawDataPort {
    long  persistRawData(RawData rawData);
}
