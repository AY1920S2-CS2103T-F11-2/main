package seedu.fitbiz.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExerciseSetsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExerciseSets(null));
    }

    @Test
    public void constructor_invalidExerciseReps_throwsIllegalArgumentException() {
        String invalidExerciseSets = "i";
        assertThrows(IllegalArgumentException.class, () -> new ExerciseSets(invalidExerciseSets));
    }

    @Test
    public void isValidExerciseSets() {
        // null weight
        assertThrows(NullPointerException.class, () -> ExerciseSets.isValidExerciseSets(null));

        // invalid weight
        assertFalse(ExerciseSets.isValidExerciseSets(" ")); // spaces only
        assertFalse(ExerciseSets.isValidExerciseSets("ab")); // contains no numbers
        assertFalse(ExerciseSets.isValidExerciseSets(".2")); // decimal numbers
        assertFalse(ExerciseReps.isValidExerciseReps("01")); // starts with 0
        assertFalse(ExerciseReps.isValidExerciseReps("10000")); // over 9999
        assertFalse(ExerciseSets.isValidExerciseSets("-23")); // negative numbers
        assertFalse(ExerciseSets.isValidExerciseSets("-23.23")); // negative decimals
        assertFalse(ExerciseSets.isValidExerciseSets("as.23")); // inclusion of letters
        assertFalse(ExerciseSets.isValidExerciseSets("23.as")); // inclusion of letters
        assertFalse(ExerciseSets.isValidExerciseSets("23.a1")); // inclusion of letters
        assertFalse(ExerciseSets.isValidExerciseSets("23z1")); // inclusion of letters
        assertFalse(ExerciseSets.isValidExerciseSets("23/.23")); // inclusion of symbols
        assertFalse(ExerciseSets.isValidExerciseSets("2-3.23")); // inclusion of symbols
        assertFalse(ExerciseSets.isValidExerciseSets("23.2'3")); // inclusion of symbols

        // valid weight
        assertTrue(ExerciseSets.isValidExerciseSets("")); // empty string
        assertTrue(ExerciseSets.isValidExerciseSets("5")); // single digit
        assertTrue(ExerciseSets.isValidExerciseSets("456")); // numbers only
        assertTrue(ExerciseReps.isValidExerciseReps("1")); // near boundary
        assertTrue(ExerciseReps.isValidExerciseReps("9999")); // near boundary
    }

}
