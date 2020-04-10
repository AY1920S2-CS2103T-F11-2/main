package seedu.fitbiz.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fitbiz.commons.core.LogsCenter;
import seedu.fitbiz.commons.exceptions.DataConversionException;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.ReadOnlyUserPrefs;
import seedu.fitbiz.model.UserPrefs;

/**
 * Manages storage of FitBiz data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FitBizStorage fitBizStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(FitBizStorage fitBizStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.fitBizStorage = fitBizStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FitBiz methods ==============================

    @Override
    public Path getFitBizFilePath() {
        return fitBizStorage.getFitBizFilePath();
    }

    @Override
    public Optional<ReadOnlyFitBiz> readFitBiz() throws DataConversionException, IOException {
        return readFitBiz(fitBizStorage.getFitBizFilePath());
    }

    @Override
    public Optional<ReadOnlyFitBiz> readFitBiz(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fitBizStorage.readFitBiz(filePath);
    }

    @Override
    public void saveFitBiz(ReadOnlyFitBiz fitBiz) throws IOException {
        saveFitBiz(fitBiz, fitBizStorage.getFitBizFilePath());
    }

    @Override
    public void saveFitBiz(ReadOnlyFitBiz fitBiz, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fitBizStorage.saveFitBiz(fitBiz, filePath);
    }

}
