package seedu.fitbiz.logic.commands;

import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
        expectedModel = new ModelManager(model.getFitBiz(), new UserPrefs(), new ClientInView());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
