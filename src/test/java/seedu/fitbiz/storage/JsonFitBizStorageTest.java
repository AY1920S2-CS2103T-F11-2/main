package seedu.fitbiz.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.fitbiz.testutil.Assert.assertThrows;
import static seedu.fitbiz.testutil.TypicalClients.ALICE;
import static seedu.fitbiz.testutil.TypicalClients.HOON;
import static seedu.fitbiz.testutil.TypicalClients.IDA;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fitbiz.commons.exceptions.DataConversionException;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.ReadOnlyFitBiz;

public class JsonFitBizStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFitBizStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFitBiz_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFitBiz(null));
    }

    private java.util.Optional<ReadOnlyFitBiz> readFitBiz(String filePath) throws Exception {
        return new JsonFitBizStorage(Paths.get(filePath)).readFitBiz(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFitBiz("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFitBiz("notJsonFormatFitBiz.json"));
    }

    @Test
    public void readFitBiz_invalidClientFitBiz_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFitBiz("invalidClientFitBiz.json"));
    }

    @Test
    public void readFitBiz_invalidAndValidClientFitBiz_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFitBiz("invalidAndValidClientFitBiz.json"));
    }

    @Test
    public void readAndSaveFitBiz_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFitBiz.json");
        FitBiz original = getTypicalFitBiz();
        JsonFitBizStorage jsonFitBizStorage = new JsonFitBizStorage(filePath);

        // Save in new file and read back
        jsonFitBizStorage.saveFitBiz(original, filePath);
        ReadOnlyFitBiz readBack = jsonFitBizStorage.readFitBiz(filePath).get();
        assertEquals(original, new FitBiz(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonFitBizStorage.saveFitBiz(original, filePath);
        readBack = jsonFitBizStorage.readFitBiz(filePath).get();
        assertEquals(original, new FitBiz(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonFitBizStorage.saveFitBiz(original); // file path not specified
        readBack = jsonFitBizStorage.readFitBiz().get(); // file path not specified
        assertEquals(original, new FitBiz(readBack));

    }

    @Test
    public void saveFitBiz_nullFitBiz_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitBiz(null, "SomeFile.json"));
    }

    /**
     * Saves {@code fitBiz} at the specified {@code filePath}.
     */
    private void saveFitBiz(ReadOnlyFitBiz fitBiz, String filePath) {
        try {
            new JsonFitBizStorage(Paths.get(filePath))
                    .saveFitBiz(fitBiz, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFitBiz_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitBiz(new FitBiz(), null));
    }
}
