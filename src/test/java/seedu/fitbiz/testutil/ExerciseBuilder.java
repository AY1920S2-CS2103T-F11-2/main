package seedu.fitbiz.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;

/**
 * A utility class to help with building Exercise objects.
 *
 * @author @yonggiee
 */
public class ExerciseBuilder {

    public static final String DEFAULT_EXERCISE_NAME = "Bench Press";
    public static final String DEFAULT_EXERCISE_REPS = "12";
    public static final String DEFAULT_EXERCISE_SETS = "4";
    public static final String DEFAULT_EXERCISE_WEIGHT = "100";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String DEFAULT_EXERCISE_DATE =
        LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER);

    private ExerciseName exerciseName;
    private ExerciseReps exerciseReps;
    private ExerciseSets exerciseSets;
    private ExerciseWeight exerciseWeight;
    private ExerciseDate exerciseDate;

    public ExerciseBuilder() {
        exerciseName = new ExerciseName(DEFAULT_EXERCISE_NAME);
        exerciseReps = new ExerciseReps(DEFAULT_EXERCISE_REPS);
        exerciseSets = new ExerciseSets(DEFAULT_EXERCISE_SETS);
        exerciseWeight = new ExerciseWeight(DEFAULT_EXERCISE_WEIGHT);
        exerciseDate = new ExerciseDate(DEFAULT_EXERCISE_DATE);
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code exerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        exerciseName = exerciseToCopy.getExerciseName();
        exerciseReps = exerciseToCopy.getExerciseReps();
        exerciseSets = exerciseToCopy.getExerciseSets();
        exerciseWeight = exerciseToCopy.getExerciseWeight();
        exerciseDate = exerciseToCopy.getExerciseDate();
    }

    /**
     * Sets the {@code ExerciseName} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withExerciseName(String exerciseName) {
        this.exerciseName = new ExerciseName(exerciseName);
        return this;
    }

    /**
     * Sets the {@code ExerciseReps} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withExerciseReps(String exerciseReps) {
        this.exerciseReps = new ExerciseReps(exerciseReps);
        return this;
    }

    /**
     * Sets the {@code ExerciseSets} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withExerciseSets(String exerciseSets) {
        this.exerciseSets = new ExerciseSets(exerciseSets);
        return this;
    }

    /**
     * Sets the {@code ExerciseWeight} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withExerciseWeight(String exerciseWeight) {
        this.exerciseWeight = new ExerciseWeight(exerciseWeight);
        return this;
    }

    /**
     * Sets the {@code ExerciseDate} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withExerciseDate(String exerciseDate) {
        this.exerciseDate = new ExerciseDate(exerciseDate);
        return this;
    }

    /**
     * Builds and returns the Exercise.
     */
    public Exercise build() {
        return new Exercise(exerciseName, exerciseReps, exerciseSets, exerciseWeight, exerciseDate);
    }

}
