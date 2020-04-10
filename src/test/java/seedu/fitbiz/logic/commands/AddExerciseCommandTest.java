package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddExerciseCommand}.
 */
public class AddExerciseCommandTest {

    private Model model;
    private Client clientInView;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
        clientInView = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
    }

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExerciseCommand(null));
    }

    @Test
    public void execute_exerciseAcceptedByModel_addSuccessful() throws Exception {
        model.setClientInView(clientInView);
        Exercise newExercise = new ExerciseBuilder().withExerciseName("Changed").build();

        CommandResult commandResult = new AddExerciseCommand(newExercise).execute(model);

        assertEquals(String.format(AddExerciseCommand.MESSAGE_SUCCESS,
            newExercise.getForOutput()),
            commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noClientInView_throwsCommandException() {
        Exercise exerciseToAdd = clientInView.getExerciseList().getExercise(INDEX_FIRST_EXERCISE);

        AddExerciseCommand addExerciseCommand = new AddExerciseCommand(exerciseToAdd);

        assertCommandFailure(addExerciseCommand, model, AddExerciseCommand.MESSAGE_CLIENT_NOT_IN_VIEW);
    }

    @Test
    public void execute_duplicateExercise_throwsCommandException() {
        model.setClientInView(clientInView);

        Exercise exerciseToAdd = clientInView.getExerciseList().getExercise(INDEX_FIRST_EXERCISE);
        AddExerciseCommand addExerciseCommand = new AddExerciseCommand(exerciseToAdd);

        assertCommandFailure(addExerciseCommand, model, AddExerciseCommand.MESSAGE_DUPLICATE_EXERCISE);
    }

    @Test
    public void equals() {
        Exercise exercise1 = new ExerciseBuilder().withExerciseName("exercise1").build();
        Exercise exercise2 = new ExerciseBuilder().withExerciseName("exercise2").build();

        AddExerciseCommand addCommand1 = new AddExerciseCommand(exercise1);
        AddExerciseCommand addCommand2 = new AddExerciseCommand(exercise2);

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same values -> returns true
        AddExerciseCommand copyAddCommand1 = new AddExerciseCommand(exercise1);
        assertTrue(addCommand1.equals(copyAddCommand1));

        // different types -> returns false
        assertFalse(addCommand1.equals(1));

        // null -> returns false
        assertFalse(addCommand1.equals(null));

        // different client -> returns false
        assertFalse(addCommand1.equals(addCommand2));
    }

}
