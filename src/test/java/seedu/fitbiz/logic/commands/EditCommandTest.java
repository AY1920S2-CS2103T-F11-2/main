package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.fitbiz.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.fitbiz.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.fitbiz.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.fitbiz.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.testutil.ClientBuilder;
import seedu.fitbiz.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and
 * RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CLIENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new FitBiz(model.getFitBiz()), new UserPrefs(),
                new ClientInView());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new FitBiz(model.getFitBiz()), new UserPrefs(),
                new ClientInView());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CLIENT, new EditCommand.EditClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new FitBiz(model.getFitBiz()), new UserPrefs(),
                new ClientInView());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CLIENT,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new FitBiz(model.getFitBiz()), new UserPrefs(),
                new ClientInView());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_CLIENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        // edit client in filtered list into a duplicate in FitBiz
        Client clientInList = model.getFitBiz().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CLIENT,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list, but
     * smaller than size of FitBiz
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of FitBiz list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitBiz().getClientList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_CLIENT, DESC_AMY);

        // same values -> returns true
        EditCommand.EditClientDescriptor copyDescriptor = new EditClientDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_CLIENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_CLIENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_CLIENT, DESC_BOB)));
    }

}
