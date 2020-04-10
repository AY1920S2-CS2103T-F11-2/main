package seedu.fitbiz.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of an exercise from a list of exercises. Guarantees:
 * immutable; is valid as declared in {@link #isValidExerciseName(String)}
 */
public class ExerciseName {

    public static final String MESSAGE_CONSTRAINTS =
        "Exercise names should only contain alphanumeric characters and spaces, and it should not be blank. "
        + "There can only be maximum of 30 characters. ";
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} ]{0,29}$";
    public final String value;

    /**
     * Constructs a {@code ExerciseName}.
     *
     * @param exerciseName A valid exercise name.
     */
    public ExerciseName(String exerciseName) {
        requireNonNull(exerciseName);
        checkArgument(isValidExerciseName(exerciseName), MESSAGE_CONSTRAINTS);
        value = exerciseName;
    }

    /**
     * Returns true if a given string is a valid exercise name.
     */
    public static boolean isValidExerciseName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseName // instanceof handles nulls
                        && value.equals(((ExerciseName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
