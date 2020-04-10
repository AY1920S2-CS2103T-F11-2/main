package seedu.fitbiz.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.exceptions.IllegalValueException;
import seedu.fitbiz.commons.util.JsonUtil;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.testutil.TypicalClients;

public class JsonSerializableFitBizStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFitBizStorageTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsFitBiz.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientFitBiz.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientFitBiz.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableFitBizStorage dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableFitBizStorage.class).get();
        FitBiz fitBizFromFile = dataFromFile.toModelType();
        FitBiz typicalClientsFitBiz = TypicalClients.getTypicalFitBiz();
        assertEquals(fitBizFromFile, typicalClientsFitBiz);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFitBizStorage dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableFitBizStorage.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableFitBizStorage dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableFitBizStorage.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFitBizStorage.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

}
