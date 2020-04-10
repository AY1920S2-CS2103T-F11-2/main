package seedu.fitbiz.logic.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * This test ensures integration between the {@code CommandHistoryStorage} and
 * {@code CommandHistoryState}. More stringent unit tests can be found in their
 * respective tests.
 */
public class CommandHistoryTest {

    private static final String EMPTY_STRING = "";
    private static final String VALID_COMMAND_1 = "edit-c 1 t/";
    private static final String VALID_COMMAND_2 = "edit-c 2 n/Nimar";

    @TempDir
    public Path testFolder;

    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory(getTempFilePath("test.txt"));
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void clearHistory_returnsCorrectOutputs() {
        commandHistory.clearHistory(); // sanity check that history is cleared
        assertEquals(commandHistory.getNextCommand(), EMPTY_STRING);
        assertEquals(commandHistory.getPreviousCommand(), EMPTY_STRING);
    }

    @Test
    public void getPreviousCommand_oneItemHistory_returnsSameString() {
        commandHistory.clearHistory(); // sanity check that history is cleared
        commandHistory.addToHistory(VALID_COMMAND_1);
        String command1 = commandHistory.getPreviousCommand();
        String command2 = commandHistory.getPreviousCommand();
        String command3 = commandHistory.getPreviousCommand();
        String command4 = commandHistory.getPreviousCommand();
        String command5 = commandHistory.getPreviousCommand();
        assertTrue(command1 == VALID_COMMAND_1
                && command2 == VALID_COMMAND_1
                && command3 == VALID_COMMAND_1
                && command4 == VALID_COMMAND_1
                && command5 == VALID_COMMAND_1);
    }

    @Test
    public void simulatedUsage_returnsCorrectOutputs() {
        commandHistory.clearHistory(); // sanity check that history is cleared
        commandHistory.addToHistory(VALID_COMMAND_1);
        commandHistory.addToHistory(VALID_COMMAND_2);

        String shouldBeValidCommand2 = commandHistory.getPreviousCommand(); // returns VALID_COMMAND_2
        assertTrue(shouldBeValidCommand2 == VALID_COMMAND_2);

        String shouldBeValidCommand1 = commandHistory.getPreviousCommand(); // returns VALID_COMMAND_1
        assertTrue(shouldBeValidCommand1 == VALID_COMMAND_1);

        shouldBeValidCommand1 = commandHistory.getPreviousCommand(); // returns VALID_COMMAND_1
        assertTrue(shouldBeValidCommand1 == VALID_COMMAND_1);

        shouldBeValidCommand2 = commandHistory.getNextCommand();
        assertTrue(shouldBeValidCommand2 == VALID_COMMAND_2);

        String shouldBeEmptyString = commandHistory.getNextCommand();
        assertTrue(shouldBeEmptyString == EMPTY_STRING);
    }
}
