package little.old.me.ingestion.domain.port.out;

import little.old.me.ingestion.domain.core.model.RawData;

import java.util.Optional;

public interface LoadRawDataPort {
    Optional<RawData> loadByPayloadChecksum(byte[] checksum);
}
