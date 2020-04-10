package seedu.fitbiz.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.schedule.ScheduleDay;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' FitBiz file path.
     */
    Path getFitBizFilePath();

    /**
     * Sets the user prefs' FitBiz file path.
     */
    void setFitBizFilePath(Path fitBizFilePath);

    /**
     * Returns the FitBiz
     */
    ReadOnlyFitBiz getFitBiz();

    /**
     * Replaces FitBiz data with the data in {@code fitBiz}.
     */
    void setFitBiz(ReadOnlyFitBiz fitBiz);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the FitBiz.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the FitBiz.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the FitBiz.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the FitBiz.
     * The client identity of {@code editedClient} must not be the same as another existing client in the FitBiz.
     */
    void setClient(Client target, Client editedClient);

    /**
     * Returns an unmodifiable view of the filtered client list
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    /**
     * Returns the client in {@code clientInView}.
     *
     * @author @yonggie
     */
    Client getClientInView();

    /**
     * Updates the client in {@code clientInView}.
     *
     * @author @yonggie
     */
    void setClientInView(Client client);

    /**
     * Clears the client in {@code clientInView}.
     *
     * @author @yonggie
     */
    void clearClientInView();

    /**
     * Returns true if a client with the same identity as {@code client} exists in
     * the FitBiz.
     *
     * @author @yonggie
     */
    boolean hasClientInView();

    /**
     * Updates client in ClientInView in the case of an EditCommand. Before updating, checks if
     * {@code editedClient} is the same client as client in ClientInView.
     *
     * @author @yonggiee
     */
    void updateClientViewIfApplicable(Client clientToEdit, Client editedClient);

    /**
     * @author @Dban1
     * Returns a view of the schedule list from across all clients.
     */
    ObservableList<ScheduleDay> getScheduleDayList();

    /*
     * Adds the given exercise to the clientInView exercise list. There must be
     * client in clientInView.
     */
    Client addExerciseToClient(Exercise exercise);

    /**
     * Replaces the {@code target} in clientInView exercise list with {@code editedExercise}.
     * There must be client in clientInView.
     */
    void editExercise (Exercise target, Exercise editedExercise);

    /**
     * Deletes the given exercise in clientInView exercise list.
     * There must be client in clientInView.
     */
    void deleteExercise(Exercise exercise);

}
