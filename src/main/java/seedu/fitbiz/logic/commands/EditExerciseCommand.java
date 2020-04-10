package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SETS;

import java.util.Optional;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.commons.util.CollectionUtil;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;
import seedu.fitbiz.model.exercise.UniqueExerciseList;

/**
 * Edits the details of an existing exercise done by the client in view.
 */
public class EditExerciseCommand extends Command {

    public static final String COMMAND_WORD = "edit-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the exercise identified "
            + "by the targetInumber used in the displayed exercise table. "
            + "There must be a client in view. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_REPS + "REPS] "
            + "[" + PREFIX_SETS + "SETS] "
            + "[" + PREFIX_EXERCISE_WEIGHT + "WEIGHT]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "pushup "
            + PREFIX_REPS + "20";

    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Edited Exercise:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in FitBiz. "
        + "Consider incrementing the sets of that existing exercise.";
    public static final String MESSAGE_CLIENT_NOT_IN_VIEW = "You currently do not have a client in view, "
        + "use the view-c command to view a client first";

    private final Index targetIndex;
    private final EditExerciseDescriptor editExerciseDescriptor;

    /**
     * @param targetIndex               of the exercise in the exercise list to edit
     * @param editExerciseDescriptor details to edit the exercise with
     */
    public EditExerciseCommand(Index targetIndex, EditExerciseDescriptor editExerciseDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editExerciseDescriptor);

        this.targetIndex = targetIndex;
        this.editExerciseDescriptor = new EditExerciseDescriptor(editExerciseDescriptor);
    }

    /**
     * Creates and returns a {@code Exercise} with the details of {@code exerciseToEdit}
     * edited with {@code editExerciseDescriptor}.
     */
    private static Exercise createEditedExercise(Exercise exerciseToEdit,
        EditExerciseDescriptor editExerciseDescriptor) {
        assert exerciseToEdit != null;

        ExerciseName updatedName = editExerciseDescriptor.getExerciseName().orElse(exerciseToEdit.getExerciseName());
        ExerciseReps updatedReps = editExerciseDescriptor.getExerciseReps().orElse(exerciseToEdit.getExerciseReps());
        ExerciseSets updatedSets = editExerciseDescriptor.getExerciseSets().orElse(exerciseToEdit.getExerciseSets());
        ExerciseWeight updatedWeight = editExerciseDescriptor.getExerciseWeight()
            .orElse(exerciseToEdit.getExerciseWeight());
        ExerciseDate updatedDate = editExerciseDescriptor.getExerciseDate().orElse(exerciseToEdit.getExerciseDate());

        return new Exercise(updatedName, updatedReps, updatedSets, updatedWeight, updatedDate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasClientInView()) {
            throw new CommandException(MESSAGE_CLIENT_NOT_IN_VIEW);
        }

        Client clientInView = model.getClientInView();
        UniqueExerciseList clientToEditExerciseList = clientInView.getExerciseList();

        if (targetIndex.getZeroBased() >= clientToEditExerciseList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
        }

        Exercise exerciseToEdit = clientToEditExerciseList.getExercise(targetIndex);
        Exercise editedExercise = createEditedExercise(exerciseToEdit, editExerciseDescriptor);

        if (!exerciseToEdit.isSameExercise(editedExercise)
            && clientToEditExerciseList.contains(editedExercise)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.editExercise(exerciseToEdit, editedExercise);
        return new CommandResult(String.format(MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise.getForOutput()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditExerciseCommand e = (EditExerciseCommand) other;
        return targetIndex.equals(e.targetIndex) && editExerciseDescriptor.equals(e.editExerciseDescriptor);
    }

    /**
     * Stores the details to edit the exercise with. Each non-empty field value will
     * replace the corresponding field value of the client.
     */
    public static class EditExerciseDescriptor {
        private ExerciseName exerciseName;
        private ExerciseReps exerciseReps;
        private ExerciseSets exerciseSets;
        private ExerciseWeight exerciseWeight;
        private ExerciseDate exerciseDate;

        public EditExerciseDescriptor() {}

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditExerciseDescriptor(EditExerciseDescriptor toCopy) {
            setExerciseName(toCopy.exerciseName);
            setExerciseReps(toCopy.exerciseReps);
            setExerciseSets(toCopy.exerciseSets);
            setExerciseWeight(toCopy.exerciseWeight);
            setExerciseDate(toCopy.exerciseDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(exerciseName, exerciseReps, exerciseSets, exerciseWeight, exerciseDate);
        }

        public Optional<ExerciseName> getExerciseName() {
            return Optional.ofNullable(exerciseName);
        }

        public void setExerciseName(ExerciseName exerciseName) {
            this.exerciseName = exerciseName;
        }

        public Optional<ExerciseReps> getExerciseReps() {
            return Optional.ofNullable(exerciseReps);
        }

        public void setExerciseReps(ExerciseReps exerciseReps) {
            this.exerciseReps = exerciseReps;
        }

        public Optional<ExerciseSets> getExerciseSets() {
            return Optional.ofNullable(exerciseSets);
        }

        public void setExerciseSets(ExerciseSets exerciseSets) {
            this.exerciseSets = exerciseSets;
        }

        public Optional<ExerciseWeight> getExerciseWeight() {
            return Optional.ofNullable(exerciseWeight);
        }

        public void setExerciseWeight(ExerciseWeight exerciseWeight) {
            this.exerciseWeight = exerciseWeight;
        }

        public Optional<ExerciseDate> getExerciseDate() {
            return Optional.ofNullable(exerciseDate);
        }

        public void setExerciseDate(ExerciseDate exerciseDate) {
            this.exerciseDate = exerciseDate;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExerciseDescriptor)) {
                return false;
            }

            // state check
            EditExerciseDescriptor e = (EditExerciseDescriptor) other;

            return getExerciseName().equals(e.getExerciseName())
                && getExerciseReps().equals(e.getExerciseReps())
                && getExerciseSets().equals(e.getExerciseSets())
                && getExerciseWeight().equals(e.getExerciseWeight())
                && getExerciseDate().equals(e.getExerciseDate());

        }
    }
}
