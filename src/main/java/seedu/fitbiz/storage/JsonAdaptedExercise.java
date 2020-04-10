package seedu.fitbiz.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fitbiz.commons.exceptions.IllegalValueException;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;

/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exercise %s field is missing!";

    private final String exerciseName;
    private final String exerciseReps;
    private final String exerciseSets;
    private final String exerciseWeight;
    private final String exerciseDate;

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given {@code Exercise}
     * details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("exerciseName") String exerciseName,
            @JsonProperty("exerciseReps") String exerciseReps, @JsonProperty("exerciseSets") String exerciseSets,
            @JsonProperty("exerciseWeight") String exerciseWeight, @JsonProperty("exerciseDate") String exerciseDate) {
        this.exerciseName = exerciseName;
        this.exerciseReps = exerciseReps;
        this.exerciseSets = exerciseSets;
        this.exerciseWeight = exerciseWeight;
        this.exerciseDate = exerciseDate;
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        exerciseName = source.getExerciseName().value;
        exerciseReps = source.getExerciseReps().value;
        exerciseSets = source.getExerciseSets().value;
        exerciseWeight = source.getExerciseWeight().value;
        exerciseDate = source.getExerciseDate().displayValue;
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's
     * {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted exercise.
     */
    public Exercise toModelType() throws IllegalValueException {
        if (exerciseName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ExerciseName.class.getSimpleName()));
        }
        if (!ExerciseName.isValidExerciseName(exerciseName)) {
            throw new IllegalValueException(ExerciseName.MESSAGE_CONSTRAINTS);
        }
        final ExerciseName modelExerciseName = new ExerciseName(exerciseName);

        if (exerciseReps == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ExerciseReps.class.getSimpleName()));
        }
        if (!ExerciseReps.isValidExerciseReps(exerciseReps)) {
            throw new IllegalValueException(ExerciseReps.MESSAGE_CONSTRAINTS);
        }
        final ExerciseReps modelExerciseReps = new ExerciseReps(exerciseReps);

        if (exerciseSets == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ExerciseSets.class.getSimpleName()));
        }
        if (!ExerciseSets.isValidExerciseSets(exerciseSets)) {
            throw new IllegalValueException(ExerciseSets.MESSAGE_CONSTRAINTS);
        }
        final ExerciseSets modelExerciseSets = new ExerciseSets(exerciseSets);

        if (exerciseWeight == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ExerciseWeight.class.getSimpleName()));
        }
        if (!ExerciseWeight.isValidExerciseWeight(exerciseWeight)) {
            throw new IllegalValueException(ExerciseWeight.MESSAGE_CONSTRAINTS);
        }
        final ExerciseWeight modelExerciseWeight = new ExerciseWeight(exerciseWeight);

        if (exerciseDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ExerciseDate.class.getSimpleName()));
        }
        if (!ExerciseDate.isValidExerciseDate(exerciseDate)) {
            throw new IllegalValueException(ExerciseDate.MESSAGE_CONSTRAINTS);
        }
        final ExerciseDate modelExerciseDate = new ExerciseDate(exerciseDate);

        return new Exercise(modelExerciseName, modelExerciseReps, modelExerciseSets, modelExerciseWeight,
                modelExerciseDate);
    }

}
