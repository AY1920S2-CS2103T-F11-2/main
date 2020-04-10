package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.schedule.Schedule;

class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ArrayList<Schedule> emptyScheduleList = new ArrayList<>();
        Client clientToSchedule = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_CLIENT, emptyScheduleList);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_CLEARED, clientToSchedule.getName().fullName);

        ModelManager expectedModel = new ModelManager(model.getFitBiz(), new UserPrefs(), new ClientInView());
        expectedModel.setClient(model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased()), clientToSchedule);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_showsCommandException() {
        ArrayList<Schedule> emptyScheduleList = new ArrayList<>();
        Client clientToSchedule = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_CLIENT, emptyScheduleList);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);

        ModelManager expectedModel = new ModelManager(model.getFitBiz(), new UserPrefs(), new ClientInView());
        expectedModel.setClient(model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased()), clientToSchedule);
        showNoClient(expectedModel);

        assertCommandFailure(scheduleCommand, expectedModel, expectedMessage);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoClient(Model model) {
        model.updateFilteredClientList(p -> false);

        assertTrue(model.getFilteredClientList().isEmpty());
    }

    @Test
    public void equals() {
        ArrayList<Schedule> scheduleListFirst = new ArrayList<>();
        ArrayList<Schedule> scheduleListSecond = new ArrayList<>();
        ScheduleCommand scheduleFirstCommand = new ScheduleCommand(INDEX_FIRST_CLIENT, scheduleListFirst);
        ScheduleCommand scheduleSecondCommand = new ScheduleCommand(INDEX_SECOND_CLIENT, scheduleListSecond);

        // same object -> returns true
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommand));

        // same values -> returns true
        ScheduleCommand scheduleFirstCommandCopy = new ScheduleCommand(INDEX_FIRST_CLIENT, scheduleListFirst);
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(scheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(scheduleFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleSecondCommand));
    }
}
