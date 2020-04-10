package seedu.fitbiz.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

/**
 * Represents the weight (in kg) of an exercise done by a client in FitBiz. Guarantees:
 * immutable; is valid as declared in {@link #isValidExerciseWeight(String)}
 */
public class ExerciseWeight {

    public static final String MESSAGE_CONSTRAINTS =
        "Input Weight must be a whole number from 1 to 9999 (eg. 65)";
    public static final String VALIDATION_REGEX = "^([1-9][0-9]{0,3})$";
    private static final String EMPTY_STRING = "";
    public final String value;

    /**
     * Constructs a {@code ExerciseWeight}.
     *
     * @param exerciseWeight A valid exerise weight.
     */
    public ExerciseWeight(String exerciseWeight) {
        requireNonNull(exerciseWeight);
        checkArgument(isValidExerciseWeight(exerciseWeight), MESSAGE_CONSTRAINTS);
        value = exerciseWeight;
    }

    /**
     * Returns true if a given string is a valid exerise weight.
     */
    public static boolean isValidExerciseWeight(String test) {
        return test.equals(EMPTY_STRING) || test.matches(VALIDATION_REGEX);
    }

    /**
     * Converts the string value of exercise weight to integer.
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
                || (other instanceof ExerciseWeight // instanceof handles nulls
                        && value.equals(((ExerciseWeight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
