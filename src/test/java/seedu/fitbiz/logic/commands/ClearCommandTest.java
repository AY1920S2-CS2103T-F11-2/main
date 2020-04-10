package seedu.fitbiz.logic.commands;

import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFitBiz_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFitBiz_success() {
        Model model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
        Model expectedModel = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
        expectedModel.setFitBiz(new FitBiz());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
