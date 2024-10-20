package little.old.me.shared.mapper;

import java.util.Optional;

@FunctionalInterface
public interface Mapper<T,V> {
    V map(T input);
}
