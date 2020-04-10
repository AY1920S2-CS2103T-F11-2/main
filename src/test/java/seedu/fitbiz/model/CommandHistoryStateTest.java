package seedu.fitbiz.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class CommandHistoryStateTest {

    private static final ArrayList<String> INITIAL_EMPTY_STATE = new ArrayList<>();
    private static final String EMPTY_STRING = "";
    private static final String VALID_COMMAND_1 = "edit-c 1 t/";
    private static final String VALID_COMMAND_2 = "edit-c 2 n/Nimar";
    private static final int LIST_SIZE_EMPTY = 0;
    private static final int LIST_SIZE_ONE = 1;
    private static final int LIST_SIZE_FIVE = 5;

    private final CommandHistoryState historyState = new CommandHistoryState(INITIAL_EMPTY_STATE);

    @Test
    public void constructor() {
        // no matter what, history must be initialised
        assertTrue(historyState.getCurrentState() != null);
    }

    @Test
    public void clearState_emptyListHistory() {
        historyState.clearState();
        assertEquals(Collections.emptyList(), historyState.getCurrentState());
    }

    @Test
    public void add_emptyString_addsNothing() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(EMPTY_STRING);
        assertTrue(historyState.getCurrentState().size() == LIST_SIZE_EMPTY);
    }

    @Test
    public void add_similarValidCommandMultipleTimes_addsOnlyOne() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_1);
        assertTrue(historyState.getCurrentState().size() == LIST_SIZE_ONE);
    }

    @Test
    public void add_validUniqueCommandMultipleTimes_addsAll() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_2);
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_2);
        historyState.add(VALID_COMMAND_1);
        assertTrue(historyState.getCurrentState().size() == LIST_SIZE_FIVE);
    }

    @Test
    public void getNextCommand_emptyHistory_returnsEmptyString() {
        historyState.clearState(); // sanity check that history is empty
        assertTrue(historyState.getNextCommand() == EMPTY_STRING);
    }

    @Test
    public void getPreviousCommand_emptyHistory_returnsEmptyString() {
        historyState.clearState(); // sanity check that history is empty
        assertTrue(historyState.getPreviousCommand() == EMPTY_STRING);
    }

    @Test
    public void getNextCommand_nonEmptyHistory_returnsEmptyString() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_2);
        assertTrue(historyState.getNextCommand() == EMPTY_STRING);
    }

    @Test
    public void getPreviousCommand_oneItemHistory_returnsSameString() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(VALID_COMMAND_1);
        String command1 = historyState.getPreviousCommand();
        String command2 = historyState.getPreviousCommand();
        String command3 = historyState.getPreviousCommand();
        String command4 = historyState.getPreviousCommand();
        String command5 = historyState.getPreviousCommand();
        assertTrue(command1 == VALID_COMMAND_1);
        assertTrue(command2 == VALID_COMMAND_1);
        assertTrue(command3 == VALID_COMMAND_1);
        assertTrue(command4 == VALID_COMMAND_1);
        assertTrue(command5 == VALID_COMMAND_1);
    }

    @Test
    public void getPreviousCommand_twoItemsHistory_returnsCorrectStrings() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_2);
        String shouldBeValidCommand2 = historyState.getPreviousCommand();
        String shouldBeValidCommand1 = historyState.getPreviousCommand();
        assertTrue(shouldBeValidCommand2 == VALID_COMMAND_2);
        assertTrue(shouldBeValidCommand1 == VALID_COMMAND_1);
    }

    @Test
    public void getNextCommand_twoItemsHistoryAfterGetPrevCommand_returnsCorrectString() {
        historyState.clearState(); // sanity check that history is empty
        historyState.add(VALID_COMMAND_1);
        historyState.add(VALID_COMMAND_2);
        historyState.getPreviousCommand(); // returns VALID_COMMAND_2
        historyState.getPreviousCommand(); // returns VALID_COMMAND_1
        String shouldBeValidCommand2 = historyState.getNextCommand();
        assertTrue(shouldBeValidCommand2 == VALID_COMMAND_2);
    }
}
