package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyFitBiz;

/**
 * A class to access FitBiz data stored as a json file on the hard disk.
 */
public class JsonFitBizStorage implements FitBizStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFitBizStorage.class);

    private Path filePath;

    public JsonFitBizStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFitBizFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFitBiz> readFitBiz() throws DataConversionException {
        return readFitBiz(filePath);
    }

    /**
     * Similar to {@link #readFitBiz()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFitBiz> readFitBiz(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFitBizStorage> jsonFitBiz = JsonUtil.readJsonFile(
                filePath, JsonSerializableFitBizStorage.class);
        if (!jsonFitBiz.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFitBiz.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFitBiz(ReadOnlyFitBiz fitBiz) throws IOException {
        saveFitBiz(fitBiz, filePath);
    }

    /**
     * Similar to {@link #saveFitBiz(ReadOnlyFitBiz)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFitBiz(ReadOnlyFitBiz fitBiz, Path filePath) throws IOException {
        requireNonNull(fitBiz);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFitBizStorage(fitBiz), filePath);
    }

}
