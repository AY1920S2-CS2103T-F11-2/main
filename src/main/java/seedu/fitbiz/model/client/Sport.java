package seedu.fitbiz.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's sport in FitBiz.
 * Guarantees: immutable; is valid as declared in {@link #isValidSport(String)}
 */
public class Sport {

    public static final String EMPTY_STRING = "";
    public static final String MESSAGE_CONSTRAINTS = "Sport should only contain alphanumeric characters and spaces";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String sportName;

    /**
     * Constructs an {@code Sport}.
     *
     * @param sport A valid Sport.
     */
    public Sport(String sport) {
        requireNonNull(sport);
        checkArgument(isValidSport(sport), MESSAGE_CONSTRAINTS);
        sportName = sport;
    }

    /**
     * Returns true if a given string is a valid sport.
     */
    public static boolean isValidSport(String test) {
        return test.equals(EMPTY_STRING) || test.matches(VALIDATION_REGEX);
    }

    public String getSportName() {
        return sportName;
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "[" + sportName + "]";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sport // instanceof handles nulls
                && sportName.equals(((Sport) other).sportName)); // state check
    }

    @Override
    public int hashCode() {
        return sportName.hashCode();
    }

}
