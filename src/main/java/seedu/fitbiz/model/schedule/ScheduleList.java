package seedu.fitbiz.model.schedule;

import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

/**
 *
 */
public class ScheduleList {

    private ArrayList<Schedule> scheduleList = new ArrayList<>();

    public ScheduleList() {
        this.scheduleList = new ArrayList<Schedule>();
    }

    public ScheduleList(ArrayList<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    /**
     * Sets schedule using an ArrayList of Schedule.
     */
    public void setSchedule(ArrayList<Schedule> newScheduleList) {
        requireAllNonNull(newScheduleList);
        scheduleList = newScheduleList;
    }

    /**
     * @author @Dban1
     * Returns a deep copy of this ScheduleList.
     * @return
     */
    public ScheduleList duplicate() {
        ScheduleList copiedList = new ScheduleList();
        copiedList.setSchedule(this.scheduleList);
        return new ScheduleList();
    }

    public void add(Schedule schedule) {
        scheduleList.add(schedule);
    }

    public ArrayList<Schedule> getArrayList() {
        return scheduleList;
    }

    @Override
    public int hashCode() {
        return scheduleList.hashCode();
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (Schedule schedule : scheduleList) {
            toReturn += schedule.toString() + "\n";
        }
        return toReturn.trim();
    }
}
