package seedu.fitbiz.testutil;

import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_DATE_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_DATE_PUSHUP;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_NAME_PUSHUP;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_REPS_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_REPS_PUSHUP;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_SETS_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_SETS_PUSHUP;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_WEIGHT_BENCH;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_WEIGHT_PUSHUP;

import seedu.fitbiz.model.exercise.Exercise;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in
 * tests.
 */
public class TypicalExercises {

    // Manually added - Exercise details found in {@code ExerciseCommandTestUtil}
    public static final Exercise PUSHUP = new ExerciseBuilder()
        .withExerciseName(VALID_EXERCISE_NAME_PUSHUP)
        .withExerciseReps(VALID_EXERCISE_REPS_PUSHUP)
        .withExerciseSets(VALID_EXERCISE_SETS_PUSHUP)
        .withExerciseWeight(VALID_EXERCISE_WEIGHT_PUSHUP)
        .withExerciseDate(VALID_EXERCISE_DATE_PUSHUP).build();
    public static final Exercise BENCH = new ExerciseBuilder()
        .withExerciseName(VALID_EXERCISE_NAME_BENCH)
        .withExerciseReps(VALID_EXERCISE_REPS_BENCH)
        .withExerciseSets(VALID_EXERCISE_SETS_BENCH)
        .withExerciseWeight(VALID_EXERCISE_WEIGHT_BENCH)
        .withExerciseDate(VALID_EXERCISE_DATE_BENCH).build();

    private TypicalExercises() {
    } // prevents instantiation

}
