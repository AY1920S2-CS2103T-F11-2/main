package seedu.fitbiz.model.schedule;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains a sorted list of Schedule for the day.
 *
 * @author @Dban1
 */
public class ScheduleDay {
    private final ArrayList<Schedule> scheduleList;
    private final String dayName;

    public ScheduleDay(ArrayList<Schedule> scheduleList, String dayName) {
        this.scheduleList = scheduleList;
        this.scheduleList.sort((o1, o2) ->
                o1.getStartTime().getDirectTimeInt() - o2.getStartTime().getDirectTimeInt());
        this.dayName = dayName;
    }

    /**
     * @author @Dban1
     * Returns a list of weekly schedule
     * @param allClientScheduleList
     * @return
     */
    public static ObservableList<ScheduleDay> weeklySchedule(ArrayList<ScheduleList> allClientScheduleList) {
        ObservableList<ScheduleDay> scheduleDayList = FXCollections.observableArrayList();

        ArrayList<Schedule> mondaySchedule = new ArrayList<>();
        ArrayList<Schedule> tuesdaySchedule = new ArrayList<>();
        ArrayList<Schedule> wednesdaySchedule = new ArrayList<>();
        ArrayList<Schedule> thursdaySchedule = new ArrayList<>();
        ArrayList<Schedule> fridaySchedule = new ArrayList<>();
        ArrayList<Schedule> saturdaySchedule = new ArrayList<>();
        ArrayList<Schedule> sundaySchedule = new ArrayList<>();

        for (ScheduleList sl : allClientScheduleList) {
            for (Schedule s : sl.getArrayList()) {
                switch (s.getDay().getDayEnum()) {
                case MON:
                    mondaySchedule.add(s);
                    break;
                case TUE:
                    tuesdaySchedule.add(s);
                    break;
                case WED:
                    wednesdaySchedule.add(s);
                    break;
                case THU:
                    thursdaySchedule.add(s);
                    break;
                case FRI:
                    fridaySchedule.add(s);
                    break;
                case SAT:
                    saturdaySchedule.add(s);
                    break;
                case SUN:
                    sundaySchedule.add(s);
                    break;
                default:
                    break;
                }
            }
        }

        scheduleDayList.add(new ScheduleDay(sundaySchedule, "SUN"));
        scheduleDayList.add(new ScheduleDay(mondaySchedule, "MON"));
        scheduleDayList.add(new ScheduleDay(tuesdaySchedule, "TUE"));
        scheduleDayList.add(new ScheduleDay(wednesdaySchedule, "WED"));
        scheduleDayList.add(new ScheduleDay(thursdaySchedule, "THU"));
        scheduleDayList.add(new ScheduleDay(fridaySchedule, "FRI"));
        scheduleDayList.add(new ScheduleDay(saturdaySchedule, "SAT"));

        return scheduleDayList;
    }

    public String getDayName() {
        return this.dayName;
    }

    public String getDayScheduleString() {
        final StringBuilder sb = new StringBuilder();
        for (Schedule s : scheduleList) {
            sb.append(s.getTimeFrame() + " " + s.getClientName() + "\n");
        }
        return sb.toString();
    }

    public int getNumberOfSchedules() {
        return this.scheduleList.size();
    }
}
