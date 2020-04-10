package seedu.fitbiz.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of Sets a client does for an exercise. Guarantees:
 * immutable; is valid as declared in {@link #isValidExerciseSets(String)}
 */
public class ExerciseSets {

    public static final String MESSAGE_CONSTRAINTS =
        "Input Sets must be a whole number from 1 to 9999 (eg. 5)";
    public static final String VALIDATION_REGEX = "^([1-9][0-9]{0,3})$";
    private static final String EMPTY_STRING = "";
    public final String value;

    /**
     * Constructs a {@code ExerciseSets}.
     *
     * @param exerciseSets A whole number.
     */
    public ExerciseSets(String xerciseSets) {
        requireNonNull(xerciseSets);
        checkArgument(isValidExerciseSets(xerciseSets), MESSAGE_CONSTRAINTS);
        value = xerciseSets;
    }

    /**
     * Returns true if a given string is a valid exercise sets.
     */
    public static boolean isValidExerciseSets(String test) {
        return test.equals(EMPTY_STRING) || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseSets // instanceof handles nulls
                        && value.equals(((ExerciseSets) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
