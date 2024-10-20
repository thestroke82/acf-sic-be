package little.old.me.shared.exception;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ExceptionSupport {

    public void logException(Exception e, String message, Object... args) {
        log.error(message, args);
        log.error("Error: {}", e.getMessage());
        log.trace("Stack trace: ", e);
    }
}
