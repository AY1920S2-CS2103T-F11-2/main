package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CommandHistoryStorageTest {

    private static final ArrayList<String> VALID_HISTORY_LIST = new ArrayList<>(List.of("1", "2", "3"));
    private static final ArrayList<String> EMPTY_HISTORY_LIST = new ArrayList<>();

    @TempDir
    public Path testFolder;

    private CommandHistoryStorage historyStorage;

    @BeforeEach
    public void setUp() {
        historyStorage = new CommandHistoryStorage(getTempFilePath("test.txt"));
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void readFromStorage_success() {
        assertDoesNotThrow(() -> historyStorage.readFromStorage());
    }

    @Test
    public void saveToStorage_nullHistory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> historyStorage.saveToStorage(null));
    }

    @Test
    public void readFromStorage_afterSavingToStorage_returnsCorrectList() {
        historyStorage.saveToStorage(VALID_HISTORY_LIST);
        ArrayList<String> storageListRead = historyStorage.readFromStorage();

        assertEquals(VALID_HISTORY_LIST.toString(), storageListRead.toString());
    }

    @Test
    void saveToStorage_calledMultipleTimes_overwritesStorage() {
        // makes sure saveToStorage is overwriting, not appending
        historyStorage.saveToStorage(VALID_HISTORY_LIST);
        ArrayList<String> read1 = historyStorage.readFromStorage();
        historyStorage.saveToStorage(VALID_HISTORY_LIST);
        ArrayList<String> read2 = historyStorage.readFromStorage();
        historyStorage.saveToStorage(VALID_HISTORY_LIST);
        ArrayList<String> read3 = historyStorage.readFromStorage();
        historyStorage.saveToStorage(VALID_HISTORY_LIST);
        ArrayList<String> read4 = historyStorage.readFromStorage();

        assertEquals(VALID_HISTORY_LIST.toString(), read1.toString());
        assertEquals(VALID_HISTORY_LIST.toString(), read2.toString());
        assertEquals(VALID_HISTORY_LIST.toString(), read3.toString());
        assertEquals(VALID_HISTORY_LIST.toString(), read4.toString());
    }

    @Test
    public void clearStorage_onInitialNonEmptyStorage_returnsEmptyList() {
        historyStorage.saveToStorage(VALID_HISTORY_LIST);
        historyStorage.clearStorage();
        ArrayList<String> storageList = historyStorage.readFromStorage();

        assertEquals(EMPTY_HISTORY_LIST.toString(), storageList.toString());
    }
}
