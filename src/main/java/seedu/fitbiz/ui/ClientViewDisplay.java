package seedu.fitbiz.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;

/**
 * An UI class which wraps {@code ClientView} and {@code ExerciseListTable}.
 * It is updated when the clientInView updates or changes.
 * @author @yonggiee
 */
public class ClientViewDisplay {

    private ClientView clientView;
    private ExerciseListTable exerciseListTable;
    private PersonalBestTable personalBestTable;

    public ClientView getClientView() {
        return clientView;
    }

    public ExerciseListTable getExerciseListTable() {
        return exerciseListTable;
    }

    public PersonalBestTable getPersonalBestTable() {
        return personalBestTable;
    }

    /**
     * Updates the client in {@code ClientView}.
     */
    public void updateClientView(Client client) {
        requireNonNull(client);
        this.clientView = new ClientView(client);
    }

    /**
     * Updates the exercise list in {@code ExerciseListTable}.
     */
    public void updateExerciseListTable(ObservableList<Exercise> exerciseList) {
        requireNonNull(exerciseList);
        this.exerciseListTable = new ExerciseListTable(exerciseList);
    }

    /**
     * Updates the personal bests of client in {@code PersonalBestTable}.
     */
    public void updatePersonalBestTable(ObservableList<Exercise> exerciseList) {
        requireNonNull(exerciseList);
        this.personalBestTable = new PersonalBestTable(exerciseList);
    }

    /**
     * Updates the client to be shown on {@code ClientViewDisplay}.
     */
    public void update(Client client) {
        updateClientView(client);
        updateExerciseListTable(client.getExerciseList().asUnmodifiableObservableList());
        updatePersonalBestTable(client.getPersonalBest().getPersonalBest());
    }
}
