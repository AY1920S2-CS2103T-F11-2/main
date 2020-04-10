package seedu.fitbiz.model.exercise;

import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an exercise done by a client. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Exercise {

    public final ExerciseName exerciseName;
    public final ExerciseReps exerciseReps;
    public final ExerciseSets exerciseSets;
    public final ExerciseWeight exerciseWeight;
    public final ExerciseDate exerciseDate;

    public Exercise(ExerciseName exerciseName, ExerciseReps exerciseReps, ExerciseSets exerciseSets,
        ExerciseWeight exerciseWeight, ExerciseDate exerciseDate) {
        requireAllNonNull(exerciseName, exerciseDate, exerciseReps, exerciseSets, exerciseWeight);
        this.exerciseName = exerciseName;
        this.exerciseReps = exerciseReps;
        this.exerciseSets = exerciseSets;
        this.exerciseWeight = exerciseWeight;
        this.exerciseDate = exerciseDate;
    }

    public ExerciseName getExerciseName() {
        return exerciseName;
    }

    public ExerciseReps getExerciseReps() {
        return exerciseReps;
    }

    public ExerciseSets getExerciseSets() {
        return exerciseSets;
    }

    public ExerciseWeight getExerciseWeight() {
        return exerciseWeight;
    }

    public ExerciseDate getExerciseDate() {
        return exerciseDate;
    }

    /**
     * Returns a string of describing {@code Exercise} to be shown in {@code ResultDisplay}
     */
    public String getForOutput() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Exercise name: ").append(getExerciseName())
            .append("\nDate: ").append(getExerciseDate())
            .append("\nReps: ").append(getExerciseReps())
            .append("\nWeight: ").append(getExerciseWeight())
            .append("\nSets: ").append(getExerciseSets());
        return builder.toString();
    }

    /**
     * Returns true if both exercises of the same name, date, reps and weight.
     * This defines a weaker notion of equality between two exercises.
     */
    public boolean isSameExercise(Exercise otherExercise) {
        if (otherExercise == this) {
            return true;
        }

        return otherExercise != null
            && otherExercise.getExerciseName().equals(getExerciseName())
            && otherExercise.getExerciseReps().equals(getExerciseReps())
            && otherExercise.getExerciseWeight().equals(getExerciseWeight())
            && otherExercise.getExerciseDate().equals(getExerciseDate());
    }

    /**
     * Returns true if both exercise have the attribute values.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exercise)) {
            return false;
        }

        Exercise otherExercise = (Exercise) other;
        return otherExercise.getExerciseName().equals(getExerciseName())
            && otherExercise.getExerciseReps().equals(getExerciseReps())
            && otherExercise.getExerciseSets().equals(getExerciseSets())
            && otherExercise.getExerciseWeight().equals(getExerciseWeight())
            && otherExercise.getExerciseDate().equals(getExerciseDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(exerciseName, exerciseReps, exerciseSets, exerciseWeight, exerciseDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Exercise name: ").append(getExerciseName())
            .append(" Date: ").append(getExerciseDate())
            .append(" Sets: ").append(getExerciseSets())
            .append(" Reps: ").append(getExerciseReps())
            .append(" Weight: ").append(getExerciseWeight());
        return builder.toString();
    }

}
