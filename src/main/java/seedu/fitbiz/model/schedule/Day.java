package seedu.fitbiz.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

import seedu.fitbiz.model.schedule.DayEnum.Weekday;
/**
 * Represents the Day of a schedule. Guarantees: immutable;
 * is valid as declared in {@link #isValidDay(String)}
 */
public class Day {

    public static final String MESSAGE_CONSTRAINTS =
            "Day should be the first 3 letters of the day, and it should not be blank";
    public static final String VALIDATION_REGEX = "[[a-zA-Z]]{3}";
    public final Weekday value;

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day with first 3 letters of the day.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        this.value = assignDay(day);
    }

    /**
     * @author Dban1
     * Private constructor for cloning Day using the Weekday enum.
     * @param day
     */
    private Day(Weekday day) {
        this.value = day;
    }

    @Override
    public Day clone() {
        return new Day(this.value);
    }

    /**
     * Checks if input String is a valid three-lettered day.
     * @param test
     * @return true if is valid day
     */
    public static boolean isValidDay(String test) {
        String lowerCaseTest = test.toLowerCase();
        switch (lowerCaseTest) {
        case "sun":
        case "mon":
        case "tue":
        case "wed":
        case "thu":
        case "fri":
        case "sat":
            return true;
        default:
            return false;
        }
    }

    /**
     * Assigns a weekday based on the day input
     * @param day
     * @return day of the week
     */
    private Weekday assignDay(String day) {
        String lowerCaseDay = day.toLowerCase();
        switch (lowerCaseDay) {
        case "sun":
            return Weekday.SUN;
        case "mon":
            return Weekday.MON;
        case "tue":
            return Weekday.TUE;
        case "wed":
            return Weekday.WED;
        case "thu":
            return Weekday.THU;
        case "fri":
            return Weekday.FRI;
        case "sat":
            return Weekday.SAT;
        default:
            return null;
        }
    }

    public Weekday getDayEnum() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
