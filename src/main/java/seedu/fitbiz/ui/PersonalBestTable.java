package seedu.fitbiz.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.fitbiz.model.exercise.Exercise;

/**
 * A UI component that displays personal bests of each exercise of a {@code Client} in a TableView.
 */
public class PersonalBestTable extends UiPart<Region> {

    private static final String FXML = "PersonalBestTable.fxml";

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
    private TableColumn<Exercise, String> exerciseName;
    @FXML
    private TableColumn<Exercise, String> exerciseDate;
    @FXML
    private TableColumn<Exercise, String> exerciseWeight;
    @FXML
    private TableColumn<Exercise, String> exerciseReps;

    public PersonalBestTable(ObservableList<Exercise> e) {
        super(FXML);
        exerciseName.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseName"));
        exerciseDate.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseDate"));
        exerciseWeight.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseWeight"));
        exerciseReps.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseReps"));
        tableView.setItems(e);
    }
}
