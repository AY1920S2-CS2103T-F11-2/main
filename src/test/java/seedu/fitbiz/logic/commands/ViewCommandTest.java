package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.Client;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToView = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, clientToView.getName().fullName);

        ModelManager expectedModel = new ModelManager(model.getFitBiz(), new UserPrefs(), new ClientInView());
        expectedModel.setClientInView(clientToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientToView = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, clientToView.getName().fullName);

        Model expectedModel = new ModelManager(model.getFitBiz(), new UserPrefs(), new ClientInView());

        showClientAtIndex(expectedModel, INDEX_FIRST_CLIENT);
        expectedModel.setClientInView(clientToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of FitBiz list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitBiz().getClientList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_CLIENT);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand deleteFirstCommandCopy = new ViewCommand(INDEX_FIRST_CLIENT);
        assertTrue(viewFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
