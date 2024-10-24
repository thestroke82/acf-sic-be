package little.old.me.shared.domain.core.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

@Getter
public enum ClaimValue {
    NOT_AVAILABLE("NA"),
    DASH("--"),
    NUMBER; // Handles numeric values like "01", "02", etc.

    private String stringValue; // Direct String, not Optional
    private Optional<Integer> numericValue;

    ClaimValue(String stringValue) {
        this.stringValue = stringValue;
        this.numericValue = Optional.empty();
    }

    ClaimValue() {
        this.stringValue = null;
        this.numericValue = Optional.empty();
    }

    public static Optional<ClaimValue> fromString(String value) {
        if (value == null) {
            return Optional.empty(); // Handle null input
        } else if (value.equals("NA")) {
            return Optional.of(NOT_AVAILABLE);
        } else if (value.equals("--")) {
            return  Optional.of(DASH);
        } else if (value.matches("\\d{2}")) { // Match "01", "02", etc.
            return  Optional.of(NUMBER.withValue(value));
        }
        return Optional.empty();
    }

    private ClaimValue withValue(@NonNull String value) {
        this.stringValue = value;
        this.numericValue = Optional.ofNullable(safeParseInt(value));
        return this;
    }

    private Integer safeParseInt(@NonNull String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null; // Return null if parsing fails
        }
    }
}
