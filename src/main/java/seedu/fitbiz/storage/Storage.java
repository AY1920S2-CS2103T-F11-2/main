package seedu.fitbiz.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fitbiz.commons.exceptions.DataConversionException;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.ReadOnlyUserPrefs;
import seedu.fitbiz.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FitBizStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFitBizFilePath();

    @Override
    Optional<ReadOnlyFitBiz> readFitBiz() throws DataConversionException, IOException;

    @Override
    void saveFitBiz(ReadOnlyFitBiz fitBiz) throws IOException;

}
