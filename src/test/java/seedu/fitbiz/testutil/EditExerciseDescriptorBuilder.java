package seedu.fitbiz.testutil;

import seedu.fitbiz.logic.commands.EditExerciseCommand;
import seedu.fitbiz.logic.commands.EditExerciseCommand.EditExerciseDescriptor;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;

/**
 * A utility class to help with building EditExerciseDescriptor objects.
 *
 * @author @yonggiee
 */
public class EditExerciseDescriptorBuilder {

    private EditExerciseDescriptor descriptor;

    public EditExerciseDescriptorBuilder() {
        descriptor = new EditExerciseCommand.EditExerciseDescriptor();
    }

    public EditExerciseDescriptorBuilder(EditExerciseDescriptor descriptor) {
        this.descriptor = new EditExerciseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExerciseDescriptor} with fields containing
     * {@code Exercise}'s details
     */
    public EditExerciseDescriptorBuilder(Exercise exercise) {
        descriptor = new EditExerciseCommand.EditExerciseDescriptor();
        descriptor.setExerciseName(exercise.getExerciseName());
        descriptor.setExerciseReps(exercise.getExerciseReps());
        descriptor.setExerciseSets(exercise.getExerciseSets());
        descriptor.setExerciseWeight(exercise.getExerciseWeight());
        descriptor.setExerciseDate(exercise.getExerciseDate());
    }

    /**
     * Sets the {@code ExerciseName} of the {@code EditExerciseDescriptor} that we
     * are building.
     */
    public EditExerciseDescriptorBuilder withExerciseName(String exerciseName) {
        descriptor.setExerciseName(new ExerciseName(exerciseName));
        return this;
    }

    /**
     * Sets the {@code ExerciseReps} of the {@code EditExerciseDescriptor} that we
     * are building.
     */
    public EditExerciseDescriptorBuilder withExerciseReps(String exerciseReps) {
        descriptor.setExerciseReps(new ExerciseReps(exerciseReps));
        return this;
    }

    /**
     * Sets the {@code ExerciseSets} of the {@code EditExerciseDescriptor} that we
     * are building.
     */
    public EditExerciseDescriptorBuilder withExerciseSets(String exerciseSets) {
        descriptor.setExerciseSets(new ExerciseSets(exerciseSets));
        return this;
    }

    /**
     * Sets the {@code ExerciseWeight} of the {@code EditExerciseDescriptor} that we
     * are building.
     */
    public EditExerciseDescriptorBuilder withExerciseWeight(String exerciseWeight) {
        descriptor.setExerciseWeight(new ExerciseWeight(exerciseWeight));
        return this;
    }

    /**
     * Sets the {@code ExerciseDate} of the {@code EditExerciseDescriptor} that we
     * are building.
     */
    public EditExerciseDescriptorBuilder withExerciseDate(String exerciseDate) {
        descriptor.setExerciseDate(new ExerciseDate(exerciseDate));
        return this;
    }

    public EditExerciseDescriptor build() {
        return descriptor;
    }
}
