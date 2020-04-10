package seedu.fitbiz.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fitbiz.model.schedule.ScheduleDay;

/**
 * An UI component that displays detailed information of a {@code Client}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX. As a consequence, UI elements' variable names cannot be
     * set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final ScheduleDay scheduleDay;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label scheduleList;

    public ScheduleCard(ScheduleDay scheduleDay) {
        super(FXML);
        this.scheduleDay = scheduleDay;
        name.setText(scheduleDay.getDayName());
        scheduleList.setText(scheduleDay.getDayScheduleString());
        scheduleList.setPrefWidth(200);
        scheduleList.setPrefHeight(20 + scheduleDay.getNumberOfSchedules() * 20);
        cardPane.setPrefHeight(45 + scheduleDay.getNumberOfSchedules() * 20);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return scheduleDay.equals(card.scheduleDay);
    }

}
