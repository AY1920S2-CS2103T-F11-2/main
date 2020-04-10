package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.testutil.TypicalClients.ALICE_DELETED_EXERCISE;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteExerciseCommand}.
 *
 * @author @yonggiee
 */
public class DeleteExerciseCommandTest {

    private Model model;
    private Client clientInView;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
        clientInView = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
    }

    @Test
    public void execute_noClientInView_throwsCommandException() {
        DeleteExerciseCommand deleteExerciseCommand = new DeleteExerciseCommand(INDEX_FIRST_EXERCISE);

        assertCommandFailure(deleteExerciseCommand, model, DeleteExerciseCommand.MESSAGE_CLIENT_NOT_IN_VIEW);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.setClientInView(clientInView);

        Exercise exerciseToDelete = clientInView.getExerciseList().getExercise(INDEX_FIRST_EXERCISE);
        DeleteExerciseCommand deleteExerciseCommand = new DeleteExerciseCommand(INDEX_FIRST_EXERCISE);

        String expectedMessage = String.format(DeleteExerciseCommand.MESSAGE_SUCCESS,
            exerciseToDelete.getForOutput());

        ModelManager expectedModel = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
        Client alice = expectedModel.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        expectedModel.setClient(alice, ALICE_DELETED_EXERCISE);

        expectedModel.setClientInView(ALICE_DELETED_EXERCISE);
        // ALICE_DELETED_EXERCISE have UniqueExerciseList with the exercise deleted

        assertCommandSuccess(deleteExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        model.setClientInView(clientInView);

        Index outOfBoundIndex = Index.fromOneBased(clientInView.getExerciseList().size() + 1);
        DeleteExerciseCommand deleteExerciseCommand = new DeleteExerciseCommand(outOfBoundIndex);

        assertCommandFailure(deleteExerciseCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExerciseCommand deleteFirstCommand = new DeleteExerciseCommand(INDEX_FIRST_EXERCISE);
        DeleteExerciseCommand deleteSecondCommand = new DeleteExerciseCommand(INDEX_SECOND_EXERCISE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteExerciseCommand deleteFirstCommandCopy = new DeleteExerciseCommand(INDEX_FIRST_EXERCISE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
