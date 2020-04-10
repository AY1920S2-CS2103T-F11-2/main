package seedu.fitbiz.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.fitbiz.commons.core.LogsCenter;
import seedu.fitbiz.model.schedule.ScheduleDay;

/**
 * Panel containing the schedule panel.
 */
public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "SchedulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    @FXML
    private ListView<ScheduleDay> schedule;

    public SchedulePanel(ObservableList<ScheduleDay> scheduleDayList) {
        super(FXML);
        schedule.setItems(scheduleDayList);
        schedule.setCellFactory(listView -> new ScheduleCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using
     * a {@code ClientCard}.
     */
    class ScheduleCell extends ListCell<ScheduleDay> {
        @Override
        protected void updateItem(ScheduleDay scheduleDay, boolean empty) {
            super.updateItem(scheduleDay, empty);

            if (empty || scheduleDay == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(scheduleDay).getRoot());
            }
        }
    }

}
