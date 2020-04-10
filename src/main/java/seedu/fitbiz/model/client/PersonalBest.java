package seedu.fitbiz.model.client;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fitbiz.model.exercise.Exercise;

/**
 * Represents the personal bests of a client's exercises in FitBiz.
 */
public class PersonalBest {
    private ObservableList<Exercise> personalBestList;

    public void setPersonalBest(Collection<Exercise> values) {
        this.personalBestList = FXCollections.observableArrayList(values);
    }

    public ObservableList<Exercise> getPersonalBest() {
        return this.personalBestList;
    }

    @Override
    public String toString() {
        String toPrint = "Personal Best \n";
        for (Exercise e : personalBestList) {
            toPrint += e.toString() + "\n";
        }
        return toPrint;
    }
}
