package seedu.fitbiz.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.fitbiz.model.client.Client;

/**
 * An UI component that displays detailed information of a {@code Client}.
 * For e.g. personal bests and age.
 */
public class ClientView extends UiPart<Region> {

    private static final String FXML = "ClientView.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    private Client client;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label name;
    @FXML
    private Label gender;
    @FXML
    private Label birthday;
    @FXML
    private Label currentWeight;
    @FXML
    private Label targetWeight;
    @FXML
    private Label height;
    @FXML
    private Label remark;
    @FXML
    private FlowPane sports;
    @FXML
    private FlowPane tags;

    public ClientView(Client client) {
        super(FXML);
        this.client = client;
        name.setText(client.getName().fullName);
        String genderLabel = "Gender: ";
        String genderAttributeForDisplay = getAttributeForDisplay(client.getGender().value);
        String fullGenderForDisplay = genderLabel + genderAttributeForDisplay;
        gender.setText(fullGenderForDisplay);
        String birthdayLabel = "Birthday: ";
        String birthdayAttributeForDisplay = getAttributeForDisplay(client.getBirthdayString());
        String fullBirthdayForDisplay = birthdayLabel + birthdayAttributeForDisplay;
        birthday.setText(fullBirthdayForDisplay);
        String heightLabel = "Height: ";
        String heightAttributeForDisplay = getAttributeForDisplay(client.getHeight().value);
        String fullHeightForDisplay = heightLabel + heightAttributeForDisplay;
        height.setText(fullHeightForDisplay);
        String currentWeightLabel = "Current Weight: ";
        String currentWeightAttributeForDisplay = getAttributeForDisplay(client.getCurrentWeight().value);
        String fullCurrentWeightForDisplay = currentWeightLabel + currentWeightAttributeForDisplay;
        currentWeight.setText(fullCurrentWeightForDisplay);
        String targetWeightLabel = "Target Weight: ";
        String targetWeightAttributeForDisplay = getAttributeForDisplay(client.getTargetWeight().value);
        String fullTargetWeightForDisplay = targetWeightLabel + targetWeightAttributeForDisplay;
        targetWeight.setText(fullTargetWeightForDisplay);
        String remarkLabel = "Remark: ";
        String remarkAttributeForDisplay = getAttributeForDisplay(client.getRemark().value);
        String fullRemarkForDisplay = remarkLabel + remarkAttributeForDisplay;
        remark.setText(fullRemarkForDisplay);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientView)) {
            return false;
        }

        // state check
        ClientView card = (ClientView) other;
        return client.equals(card.client);
    }

    private String getAttributeForDisplay(String string) {
        String emptyString = "";
        return !string.equals(emptyString) ? string : "-";
    }
}
