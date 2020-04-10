package seedu.fitbiz.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.fitbiz.model.exercise.Exercise;

/**
 * A UI component that displays exercises of a {@code Client} in a TableView.
 */
public class ExerciseListTable extends UiPart<Region> {

    private static final String FXML = "ExerciseListTable.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    @FXML
    private TableView<Exercise> tableView;
    @FXML
    private TableColumn<Exercise, String> id;
    @FXML
    private TableColumn<Exercise, String> exerciseName;
    @FXML
    private TableColumn<Exercise, String> exerciseDate;
    @FXML
    private TableColumn<Exercise, String> exerciseSets;
    @FXML
    private TableColumn<Exercise, String> exerciseReps;
    @FXML
    private TableColumn<Exercise, String> exerciseWeight;

    public ExerciseListTable(ObservableList<Exercise> e) {
        super(FXML);
        id.setCellValueFactory(data -> {
            Exercise exercise = data.getValue();
            int index = e.indexOf(exercise) + 1;
            return new SimpleStringProperty(Integer.toString(index));
        });
        exerciseName.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseName"));
        exerciseDate.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseDate"));
        exerciseSets.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseSets"));
        exerciseReps.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseReps"));
        exerciseWeight.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseWeight"));
        tableView.setItems(e);
    }
}
