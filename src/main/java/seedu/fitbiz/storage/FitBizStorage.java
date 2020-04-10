package seedu.fitbiz.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fitbiz.commons.exceptions.DataConversionException;
import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.ReadOnlyFitBiz;

/**
 * Represents a storage for {@link FitBiz}.
 */
public interface FitBizStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFitBizFilePath();

    /**
     * Returns FitBiz data as a {@link ReadOnlyFitBiz}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFitBiz> readFitBiz() throws DataConversionException, IOException;

    /**
     * @see #getFitBizFilePath()
     */
    Optional<ReadOnlyFitBiz> readFitBiz(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFitBiz} to the storage.
     * @param fitBiz cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFitBiz(ReadOnlyFitBiz fitBiz) throws IOException;

    /**
     * @see #saveFitBiz(ReadOnlyFitBiz)
     */
    void saveFitBiz(ReadOnlyFitBiz fitBiz, Path filePath) throws IOException;

}
