package seedu.fitbiz.model;

import java.nio.file.Path;

import seedu.fitbiz.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFitBizFilePath();

}
