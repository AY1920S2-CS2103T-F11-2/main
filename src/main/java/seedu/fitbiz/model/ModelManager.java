package seedu.fitbiz.model;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.commons.core.LogsCenter;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.UniqueExerciseList;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.ScheduleDay;
import seedu.fitbiz.model.schedule.ScheduleList;

/**
 * Represents the in-memory model of the FitBiz data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FitBiz fitBiz;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final ClientInView clientInView;

    /**
     * Initializes a ModelManager with the given fitBiz and userPrefs.
     */
    public ModelManager(ReadOnlyFitBiz fitBiz, ReadOnlyUserPrefs userPrefs, ClientInView clientInView) {
        super();
        requireAllNonNull(fitBiz, userPrefs);

        logger.fine("Initializing with FitBiz: " + fitBiz + " and user prefs " + userPrefs);

        this.fitBiz = new FitBiz(fitBiz);
        this.userPrefs = new UserPrefs(userPrefs);
        this.clientInView = clientInView;
        filteredClients = new FilteredList<>(this.fitBiz.getClientList());
    }

    public ModelManager() {
        this(new FitBiz(), new UserPrefs(), new ClientInView());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFitBizFilePath() {
        return userPrefs.getFitBizFilePath();
    }

    @Override
    public void setFitBizFilePath(Path fitBizFilePath) {
        requireNonNull(fitBizFilePath);
        userPrefs.setFitBizFilePath(fitBizFilePath);
    }

    //=========== FitBiz ================================================================================

    @Override
    public void setFitBiz(ReadOnlyFitBiz fitBiz) {
        this.fitBiz.resetData(fitBiz);
    }

    @Override
    public ReadOnlyFitBiz getFitBiz() {
        return fitBiz;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return fitBiz.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        if (target == getClientInView()) {
            clearClientInView();
        }
        fitBiz.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        fitBiz.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);
        if (hasClientInView() && getClientInView().equals(target)) {
            clientInView.setClient(editedClient);
        }
        fitBiz.setClient(target, editedClient);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedFitBiz}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    //=========== ClientInView ================================================================================

    @Override
    public Client getClientInView() {
        return clientInView.getClient();
    }

    @Override
    public void setClientInView(Client client) {
        clientInView.setClient(client);
    }

    @Override
    public void clearClientInView() {
        setClientInView(null);
    }

    @Override
    public boolean hasClientInView() {
        return clientInView.hasClientInView();
    }

    @Override
    public void updateClientViewIfApplicable(Client clientToEdit, Client editedClient) {
        requireAllNonNull(clientToEdit, editedClient);
        if (!clientInView.hasClientInView()) {
            return;
        }
        Client currentClientInView = clientInView.getClient();
        if (currentClientInView.equals(clientToEdit)) {
            clientInView.setClient(editedClient);
        }
    }

    //=========== ScheduleList ==================================================================================
    @Override
    public ObservableList<ScheduleDay> getScheduleDayList() {
        ArrayList<ScheduleList> fullScheduleList = new ArrayList<>();
        for (Client c: filteredClients) {
            for (Schedule s: c.getScheduleList().getArrayList()) {
                s.assignClientName(c.getName().fullName);
            }
            fullScheduleList.add(c.getScheduleList());
        }
        return ScheduleDay.weeklySchedule(fullScheduleList);
    };

    //=========== Exercise ================================================================================

    /**
     * Creates a new {@code Client} with the new exercise list. Other attributes remain the
     * same.
     */
    private Client buildClientWithNewExerciseList(Client clientToEdit,
        UniqueExerciseList clientToEditExerciseList) {

        Client editedClient = new Client(clientToEdit.getName(), clientToEdit.getGender(), clientToEdit.getPhone(),
            clientToEdit.getEmail(), clientToEdit.getAddress(), clientToEdit.getTags(), clientToEdit.getBirthday(),
            clientToEdit.getCurrentWeight(), clientToEdit.getTargetWeight(), clientToEdit.getHeight(),
            clientToEdit.getRemark(), clientToEdit.getSports(), clientToEditExerciseList,
            clientToEdit.getPersonalBest(), clientToEdit.getScheduleList());

        return editedClient;
    }

    @Override
    public Client addExerciseToClient(Exercise exercise) {
        requireNonNull(exercise);

        Client clientToEdit = getClientInView();
        UniqueExerciseList clientToEditExerciseList = clientToEdit.getExerciseList();
        clientToEditExerciseList.addToSorted(exercise);

        Client editedClient = buildClientWithNewExerciseList(clientToEdit, clientToEditExerciseList);

        setClient(clientToEdit, editedClient);
        return editedClient;
    }

    @Override
    public void editExercise (Exercise target, Exercise editedExercise) {
        Client clientToEdit = getClientInView();
        UniqueExerciseList clientToEditExerciseList = clientToEdit.getExerciseList();

        clientToEditExerciseList.setExercise(target, editedExercise);
    }

    @Override
    public void deleteExercise (Exercise exercise) {
        requireNonNull(exercise);
        Client clientToEdit = getClientInView();
        UniqueExerciseList clientToEditExerciseList = clientToEdit.getExerciseList();

        // mutates the list belonging to the client by removing the exercise
        clientToEditExerciseList.remove(exercise);

        Client editedClient = buildClientWithNewExerciseList(clientToEdit, clientToEditExerciseList);

        setClient(clientToEdit, editedClient);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return fitBiz.equals(other.fitBiz)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && clientInView.equals(other.clientInView);
    }

}
