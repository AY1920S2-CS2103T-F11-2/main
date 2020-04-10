package seedu.fitbiz.testutil;

import java.util.ArrayList;

import seedu.fitbiz.model.schedule.Day;
import seedu.fitbiz.model.schedule.EndTime;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.ScheduleList;
import seedu.fitbiz.model.schedule.StartTime;

/**
 * A utility class to help build Schedules.
 */
public class ScheduleBuilder {

    public static final String DEFAULT_DAY = ("MON");
    public static final String DEFAULT_START_TIME = "1100";
    public static final String DEFAULT_END_TIME = "1200";

    private Day day;
    private StartTime startTime;
    private EndTime endTime;

    public ScheduleBuilder() {
        day = new Day(DEFAULT_DAY);
        startTime = new StartTime(DEFAULT_START_TIME);
        endTime = new EndTime(DEFAULT_END_TIME);
    }

    public Schedule build() {
        return new Schedule(day, startTime, endTime);
    }

    /**
     * Wraps the current Schedule in a ScheduleList.
     * @return
     */
    public ScheduleList buildAsList() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(this.build());
        return new ScheduleList(scheduleList);
    }

    /**
     * Updates the Day of the ScheduleBuilder.
     * @param day
     * @return
     */
    public ScheduleBuilder withDay(String day) {
        this.day = new Day(day);
        return this;
    }


    /**
     * Updates the Start Time of the ScheduleBuilder.
     * @param st
     * @return
     */
    public ScheduleBuilder withStartTime(String st) {
        this.startTime = new StartTime(st);
        return this;
    }

    /**
     * Updates the End Time of the ScheduleBuilder.
     * @param et
     * @return
     */
    public ScheduleBuilder withEndTime(String et) {
        this.endTime = new EndTime(et);
        return this;
    }

}
