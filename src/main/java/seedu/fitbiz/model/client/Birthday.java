package seedu.fitbiz.model.client;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Client's birthday in FitBiz.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {

    public static final String EARLIEST_BIRTHDAY = LocalDate.now().minusYears(120)
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should be in the format DD-MM-YYYY (eg. 02-03-1999), and cannot exceed the current date."
            + " Birthday should also not be earlier than " + EARLIEST_BIRTHDAY + ".";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate value;
    public final String displayValue;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birth date in the form DD-MM-YYYY.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.value = birthday.isEmpty() ? null : LocalDate.parse(birthday, DATE_TIME_FORMATTER);
        this.displayValue = birthday; //assuming birthday string is valid
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        LocalDate testBirthday;
        if (test.isEmpty()) {
            return true;
        }
        try {
            testBirthday = LocalDate.parse(test, DATE_TIME_FORMATTER);
            LocalDate dateNow = LocalDate.now();
            LocalDate dateNowMinusHundredTwentyYear = dateNow.minusYears(120);
            if (dateNow.compareTo(testBirthday) <= 0) {
                return false;
            } else if (dateNowMinusHundredTwentyYear.isAfter(testBirthday)) {
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "";
        }
        return this.value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isEmpty() {
        return value == null;
    }

    public String getAge() {
        if (this.value == null) {
            return "-";
        }
        long age = DAYS.between(this.value, LocalDate.now()) / 365;
        return Long.toString(age);
    }

}
