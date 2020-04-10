package seedu.fitbiz.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_DATE_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_REPS_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_SETS_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_WEIGHT_BENCH;
import static seedu.fitbiz.testutil.TypicalExercises.BENCH;
import static seedu.fitbiz.testutil.TypicalExercises.PUSHUP;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.testutil.ExerciseBuilder;

public class ExerciseTest {

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(PUSHUP.isSameExercise(PUSHUP));

        // null -> returns false
        assertFalse(PUSHUP.isSameExercise(null));

        // different reps, different date, different weight -> returns false
        Exercise editedPushup = new ExerciseBuilder(PUSHUP)
            .withExerciseReps(VALID_EXERCISE_REPS_BENCH)
            .withExerciseDate(VALID_EXERCISE_DATE_BENCH)
            .withExerciseWeight(VALID_EXERCISE_WEIGHT_BENCH)
            .build();
        assertFalse(PUSHUP.isSameExercise(editedPushup));

        // different name -> returns false
        editedPushup = new ExerciseBuilder(PUSHUP).withExerciseName(VALID_EXERCISE_NAME_BENCH).build();
        assertFalse(PUSHUP.isSameExercise(editedPushup));

        // same name, same date, same weight, same reps, different sets -> returns true
        editedPushup = new ExerciseBuilder(PUSHUP).withExerciseSets(VALID_EXERCISE_SETS_BENCH).build();
        assertTrue(PUSHUP.isSameExercise(editedPushup));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise pushupCopy = new ExerciseBuilder(PUSHUP).build();
        assertTrue(PUSHUP.equals(pushupCopy));

        // same object -> returns true
        assertTrue(PUSHUP.equals(PUSHUP));

        // null -> returns false
        assertFalse(PUSHUP.equals(null));

        // different type -> returns false
        assertFalse(PUSHUP.equals(5));

        // different Exercise -> returns false
        assertFalse(PUSHUP.equals(BENCH));

        // different Name -> returns false
        Exercise editedPushup = new ExerciseBuilder(PUSHUP).withExerciseName(VALID_EXERCISE_NAME_BENCH).build();
        assertFalse(PUSHUP.equals(editedPushup));

        // different reps -> returns false
        editedPushup = new ExerciseBuilder(PUSHUP).withExerciseReps(VALID_EXERCISE_REPS_BENCH).build();
        assertFalse(PUSHUP.equals(editedPushup));

        // different weight -> returns false
        editedPushup = new ExerciseBuilder(PUSHUP).withExerciseWeight(VALID_EXERCISE_WEIGHT_BENCH).build();
        assertFalse(PUSHUP.equals(editedPushup));

        // different date -> returns false
        editedPushup = new ExerciseBuilder(PUSHUP).withExerciseDate(VALID_EXERCISE_DATE_BENCH).build();
        assertFalse(PUSHUP.equals(editedPushup));

    }
}
