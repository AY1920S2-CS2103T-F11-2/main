package seedu.fitbiz.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of Reps a client does for an exercise. Guarantees:
 * immutable; is valid as declared in {@link #isValidExerciseReps(String)}
 */
public class ExerciseReps {

    public static final String MESSAGE_CONSTRAINTS =
        "Input Reps must be a whole number from 1 to 9999 (eg. 65)";
    public static final String VALIDATION_REGEX = "^([1-9][0-9]{0,3})$";
    private static final String EMPTY_STRING = "";
    public final String value;

    /**
     * Constructs a {@code ExerciseReps}.
     *
     * @param exerciseReps A positive whole number.
     */
    public ExerciseReps(String exerciseReps) {
        requireNonNull(exerciseReps);
        checkArgument(isValidExerciseReps(exerciseReps), MESSAGE_CONSTRAINTS);
        value = exerciseReps;
    }

    /**
     * Returns true if a given string is a valid exercise reps.
     */
    public static boolean isValidExerciseReps(String test) {
        return test.equals(EMPTY_STRING) || test.matches(VALIDATION_REGEX);
    }

    /**
     * Converts the string value of exercise reps to integer.
     */
    public int convertToInt() {
        if (value.equals(EMPTY_STRING)) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseReps // instanceof handles nulls
                        && value.equals(((ExerciseReps) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
