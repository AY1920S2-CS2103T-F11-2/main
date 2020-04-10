package seedu.fitbiz.logic.commands;

import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(model.getFitBiz(), new UserPrefs(), new ClientInView());
        expectedModel.addClient(validClient);

        assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getFitBiz().getClientList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
