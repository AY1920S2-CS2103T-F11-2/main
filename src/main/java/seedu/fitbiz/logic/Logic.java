package seedu.fitbiz.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.logic.commands.CommandResult;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.schedule.ScheduleDay;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the FitBiz.
     *
     * @see seedu.fitbiz.model.Model#getFitBiz()
     */
    ReadOnlyFitBiz getFitBiz();

    /**
     * Returns an unmodifiable view of the filtered list of clients
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * @author @Dban1
     * Returns all schedules across all clients according to the week day.
     *
     * @return
     */
    ObservableList<ScheduleDay> getScheduleDayList();

    /**
     * Returns the client required by {@code ViewCommand} from {@code ModelManager}.
     *
     * @author @yonggiee
     */
    Client getClientInView();

    /**
     * Returns true if there is a client in {@code clientInView} in
     * {@code ModelManager}.
     *
     * @author @yonggiee
     */
    Boolean hasClientInView();

    /**
     * Returns the user prefs' FitBiz file path.
     */
    Path getFitBizFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Opens the provided {@code url} in the user's default web browser.
     *
     * @param url website url to open
     */
    public void openUrlInDefaultWebBrowser(String url);
}
