package seedu.fitbiz.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExerciseRepsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExerciseReps(null));
    }

    @Test
    public void constructor_invalidExerciseReps_throwsIllegalArgumentException() {
        String invalidExerciseReps = "i";
        assertThrows(IllegalArgumentException.class, () -> new ExerciseReps(invalidExerciseReps));
    }

    @Test
    public void isValidExerciseReps() {
        // null reps
        assertThrows(NullPointerException.class, () -> ExerciseReps.isValidExerciseReps(null));

        // invalid reps
        assertFalse(ExerciseReps.isValidExerciseReps(" ")); // spaces only
        assertFalse(ExerciseReps.isValidExerciseReps("ab")); // contains no numbers
        assertFalse(ExerciseReps.isValidExerciseReps(".2")); // decimal numbers
        assertFalse(ExerciseReps.isValidExerciseReps("01")); // starts with 0
        assertFalse(ExerciseReps.isValidExerciseReps("10000")); // over 9999
        assertFalse(ExerciseReps.isValidExerciseReps("-23")); // negative numbers
        assertFalse(ExerciseReps.isValidExerciseReps("-23.23")); // negative decimals
        assertFalse(ExerciseReps.isValidExerciseReps("as.23")); // inclusion of letters
        assertFalse(ExerciseReps.isValidExerciseReps("23.as")); // inclusion of letters
        assertFalse(ExerciseReps.isValidExerciseReps("23.a1")); // inclusion of letters
        assertFalse(ExerciseReps.isValidExerciseReps("23z1")); // inclusion of letters
        assertFalse(ExerciseReps.isValidExerciseReps("23/.23")); // inclusion of symbols
        assertFalse(ExerciseReps.isValidExerciseReps("2-3.23")); // inclusion of symbols
        assertFalse(ExerciseReps.isValidExerciseReps("23.2'3")); // inclusion of symbols

        // valid reps
        assertTrue(ExerciseReps.isValidExerciseReps("")); // empty string
        assertTrue(ExerciseReps.isValidExerciseReps("5")); // single digit
        assertTrue(ExerciseReps.isValidExerciseReps("456")); // numbers only
        assertTrue(ExerciseReps.isValidExerciseReps("1")); // near boundary
        assertTrue(ExerciseReps.isValidExerciseReps("9999")); // near boundary
    }

}
