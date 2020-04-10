package seedu.fitbiz.logic.history;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.fitbiz.model.CommandHistoryState;
import seedu.fitbiz.storage.StorageReaderWriter;

/**
 * This class contains the logic behind the command history feature and exposes
 * the necessary methods needed.
 */
public class CommandHistory {

    private static final Path DEFAULT_STORAGE_FILE_PATH = Paths.get("data", "command.txt");

    private StorageReaderWriter storageReaderWriter;
    private CommandHistoryState historyState;

    /**
     * Default constructor for this class which uses the default storage file path
     * for storage {@code StorageReaderWriter}.
     */
    public CommandHistory() {
        storageReaderWriter = new StorageReaderWriter(DEFAULT_STORAGE_FILE_PATH);
        historyState = new CommandHistoryState(storageReaderWriter.readFromStorage());
    }

    /**
     * Overloaded constructor for this class which allows the storage file path for
     * storage {@code StorageReaderWriter} to be set. Provided for ease of JUnit
     * testing.
     */
    public CommandHistory(Path storagePath) {
        storageReaderWriter = new StorageReaderWriter(storagePath);
        historyState = new CommandHistoryState(storageReaderWriter.readFromStorage());
    }

    /**
     * Adds the user input to the history, saving it to both the model via
     * {@code CommandHistoryState} and the storage via {@code StorageReaderWriter}.
     *
     * @param command user input command to store
     */
    public void addToHistory(String command) {
        historyState.add(command);
        storageReaderWriter.writeToStorage(historyState.getCurrentState());
    }

    /**
     * Returns the previous (least recent) command while browsing the history.
     */
    public String getPreviousCommand() {
        return historyState.getPreviousCommand();
    }

    /**
     * Returns the next (most recent) command while browsing the history.
     */
    public String getNextCommand() {
        return historyState.getNextCommand();
    }

    /**
     * Clears both the model via {@code CommandHistoryState} and the storage file
     * via {@code StorageReaderWriter}.
     */
    public void clearHistory() {
        historyState.clearState();
        storageReaderWriter.clearStorage();
    }
}
