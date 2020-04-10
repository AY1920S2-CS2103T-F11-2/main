package seedu.fitbiz.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFitBizStorage fitBizStorage = new JsonFitBizStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(fitBizStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void fitBizReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFitBizStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFitBizStorageTest} class.
         */
        FitBiz original = getTypicalFitBiz();
        storageManager.saveFitBiz(original);
        ReadOnlyFitBiz retrieved = storageManager.readFitBiz().get();
        assertEquals(original, new FitBiz(retrieved));
    }

    @Test
    public void getFitBizFilePath() {
        assertNotNull(storageManager.getFitBizFilePath());
    }

}
