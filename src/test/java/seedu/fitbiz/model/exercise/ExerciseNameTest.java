package seedu.fitbiz.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExerciseNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExerciseName(null));
    }

    @Test
    public void constructor_invalidExerciseName_throwsIllegalArgumentException() {
        String invalidExerciseName = "";
        assertThrows(IllegalArgumentException.class, () -> new ExerciseName(invalidExerciseName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ExerciseName.isValidExerciseName(null));

        // invalid name
        assertFalse(ExerciseName.isValidExerciseName("")); // empty string
        assertFalse(ExerciseName.isValidExerciseName(" ")); // spaces only
        assertFalse(ExerciseName.isValidExerciseName("^")); // only non-alphanumeric characters
        assertFalse(ExerciseName.isValidExerciseName("pushups*")); // contains non-alphanumeric characters
        assertFalse(ExerciseName.isValidExerciseName("pushuppushuppushuppushuppushup1")); // 31 characters

        // valid name
        assertTrue(ExerciseName.isValidExerciseName("bench press")); // alphabets only
        assertTrue(ExerciseName.isValidExerciseName("12345")); // numbers only
        assertTrue(ExerciseName.isValidExerciseName("bench press 2nd")); // alphanumeric characters
        assertTrue(ExerciseName.isValidExerciseName("Bench Press")); // with capital letters
        assertTrue(ExerciseName.isValidExerciseName("pushuppushuppushuppushuppushup")); // 30 chars
    }
}
