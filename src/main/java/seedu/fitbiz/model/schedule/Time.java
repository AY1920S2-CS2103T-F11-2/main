package seedu.fitbiz.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;


/**
 * Abstract Time class that contains the basic implementations for Time management.
 */
public abstract class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "Time input should be in the format HHMM and it should not be blank";
    public final String value;
    public final int directTimeInt;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid end time in the form HHMM
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTimingFormat(time), MESSAGE_CONSTRAINTS);
        this.value = formatTime(time);
        this.directTimeInt = Integer.parseInt(time);
    }

    /**
     * Checks if the input timing is of valid "HHMM" format.
     * * @param time
     *
     * @return true if time is of correct format of "HHMM"
     */
    public static boolean isValidTimingFormat(String time) {
        try {
            if (time.length() != 4) {
                return false;
            }
            String formattedTime = time.substring(0, 2) + ":" + time.substring(2, 4);
            LocalTime.parse(formattedTime);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Formats a HHMM time to HH:MM
     *
     * @param time time in string
     * @return formatted time in HH:MM
     */
    public static String formatTime(String time) {
        String formattedEndTime = time.substring(0, 2) + ":" + time.substring(2, 4);
        return formattedEndTime;
    }

    public String getTime() {
        String time = value.substring(0, 2) + value.substring(3, 5);
        return time;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    public int getDirectTimeInt() {
        return this.directTimeInt;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
