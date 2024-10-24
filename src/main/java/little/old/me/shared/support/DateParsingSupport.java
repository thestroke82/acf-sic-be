package little.old.me.shared.support;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateParsingSupport {

    public static final String DATE_FORMAT = "dd\\mm\\yyyy";
    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " HH:mm";


    public static Optional<LocalDateTime> parseLocalDateTime(String dateString) {
        return parseLocalDateTime(dateString, DATE_TIME_FORMAT);
    }

    public static Optional<LocalDateTime> parseLocalDateTime(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return Optional.of(LocalDateTime.parse(dateString, formatter));
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public static Optional<LocalDate> parseLocalDate(String dateString) {
        return parseLocalDate(dateString, DATE_FORMAT);
    }

    public static Optional<LocalDate> parseLocalDate(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return Optional.of(LocalDate.parse(dateString, formatter));
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
