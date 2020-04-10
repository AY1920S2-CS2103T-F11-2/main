package seedu.fitbiz.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date of an exercise done by client. Guarantees: immutable; is
 * valid as declared in {@link #isValidExerciseDate(String)}
 */
public class ExerciseDate {

    public static final String EARLIEST_DATE = LocalDate.now().minusYears(1)
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    public static final String MESSAGE_CONSTRAINTS =
            "Exercise date should be in the format DD-MM-YYYY (eg. 02-03-1999), and cannot exceed the current date."
                    + " Exercise date should also not be earlier than " + EARLIEST_DATE + ".";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate value;
    public final String displayValue;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date in the form DD-MM-YYYY.
     */
    public ExerciseDate(String date) {
        requireNonNull(date);
        checkArgument(isValidExerciseDate(date), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(date, DATE_TIME_FORMATTER);
        this.displayValue = date; // assuming birthday string is valid
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidExerciseDate(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test, DATE_TIME_FORMATTER);
            LocalDate dateNow = LocalDate.now();
            LocalDate dateNowMinusOneYear = dateNow.minusYears(1);

            if (dateNow.isBefore(testDate)) {
                return false;
            } else if (dateNowMinusOneYear.isAfter(testDate)) {
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseDate // instanceof handles nulls
                        && value.equals(((ExerciseDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
