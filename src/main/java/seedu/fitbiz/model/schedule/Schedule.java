package seedu.fitbiz.model.schedule;

import static seedu.fitbiz.commons.util.AppUtil.checkArgument;
import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


/**
 * Represents a Schedule in the Client.
 */
public class Schedule implements Comparable<Schedule> {
    public static final String MESSAGE_CONSTRAINTS = "Schedule day should be the first 3 letters of the day. "
            + "Timings should be given in 24 hour format and in HHmm format. "
            + "Start time cannot be later than end time.\nMaximum range is 0000-2359."
            + " Example usage: schedule 1 sch/MON-1100-1200";

    private final Day day;
    private final StartTime startTime;
    private final EndTime endTime;
    private String clientName = "unnamed";

    public Schedule(Day day, StartTime startTime, EndTime endTime) {
        requireAllNonNull(day, startTime, endTime);
        checkArgument(isValidTimeFrame(startTime.toString(), endTime.toString()), MESSAGE_CONSTRAINTS);
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @author Dban1
     * @param startTime
     * @param endTime
     * @return Checks if end time is later than start time, assuming they are of valid format "hhmm". End time
     * and start time cannot be simultaneously 0000. If end time is 0000, it is assumed as the next day.
     */
    public static boolean isValidTimeFrame(String startTime, String endTime) {
        // End time cannot be 0000
        if (endTime.equals("00:00")) {
            return false;
        }
        // Start time cannot equal End time
        if (startTime.equals(endTime)) {
            return false;
        }
        // Check if End time is later than Start time
        return (LocalTime.parse(endTime).compareTo(LocalTime.parse(startTime)) > 0);
    }

    /**
     * @author @Dban1
     * Returns a schedule with a client name tagged to it.
     *
     * @param s
     * @return
     */
    public void assignClientName(String s) {
        this.clientName = s;
    }

    /**
     * @author tohkerwei
     * @param scheduleString a schedule in string format
     * @return a schedule object
     */
    public static Schedule stringToSchedule(String scheduleString) {
        String[] tokens = scheduleString.split(" ", 0);
        Day day = new Day(tokens[0]);
        StartTime startTime = new StartTime(tokens[2].substring(0, 2) + tokens[2].substring(3, 5));
        EndTime endTime = new EndTime(tokens[4].substring(0, 2) + tokens[4].substring(3, 5));
        return new Schedule(day, startTime, endTime);
    }

    public Day getDay() {
        return this.day;
    }

    public StartTime getStartTime() {
        return this.startTime;
    }

    public EndTime getEndTime() {
        return this.endTime;
    }

    public double getHours() {
        LocalTime start = LocalTime.parse(this.startTime.getTime());
        LocalTime end = LocalTime.parse(this.endTime.getTime());
        return (double) ChronoUnit.HOURS.between(start, end);
    }

    /**
     * @return
     * @author Dban1
     * Returns a clone of Schedule.
     */
    @Override
    public Schedule clone() {
        Day clonedDay = this.day.clone();
        StartTime clonedStartTime = this.startTime.clone();
        EndTime clonedEndTime = this.endTime.clone();
        String clonedClientName = this.clientName;

        Schedule clonedSchedule = new Schedule(clonedDay, clonedStartTime, clonedEndTime);
        clonedSchedule.assignClientName(clonedClientName);

        return clonedSchedule;
    }

    /**
     * Returns true if both schedule have the same attribute values
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }


        Schedule otherSchedule = (Schedule) other;
        int otherSt = otherSchedule.getStartTime().getDirectTimeInt();
        int otherEt = otherSchedule.getEndTime().getDirectTimeInt();
        int thisSt = this.getStartTime().getDirectTimeInt();
        int thisEt = this.getEndTime().getDirectTimeInt();

        // Checks if there are overlaps between 2 schedules.
        return otherSchedule.getDay().equals(getDay())
                && (thisSt == otherSt)
                && (thisEt == otherEt);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(day, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDay())
                .append(" Time: ")
                .append(getStartTime())
                .append(" - ")
                .append(getEndTime());
        return builder.toString();
    }

    public String getTimeFrame() {
        String timeFrame = getStartTime().toString() + " - " + getEndTime().toString();
        return timeFrame;
    }

    @Override
    public int compareTo(Schedule other) {
        Schedule otherSchedule = other;
        int otherSt = other.getStartTime().getDirectTimeInt();
        int otherEt = other.getEndTime().getDirectTimeInt();
        int thisSt = this.getStartTime().getDirectTimeInt();
        int thisEt = this.getEndTime().getDirectTimeInt();
        // Checks if there are overlaps between 2 schedules.
        if (otherSchedule.getDay().equals(getDay())) {
            if (((otherSt < thisSt) && (otherEt < thisSt)) || (otherSt > thisEt)) {
                return otherSt - thisSt;
            } else {
                return 0;
            }
        } else {
            DayEnum.Weekday otherWeekday = otherSchedule.getDay().getDayEnum();
            DayEnum.Weekday thisWeekday = this.getDay().getDayEnum();
            return otherWeekday.compareTo(thisWeekday);
        }
    }

    public String getClientName() {
        return this.clientName;
    }
}
