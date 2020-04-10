package seedu.fitbiz.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class ExerciseDateTest {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate DATE_NOW = LocalDate.now();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExerciseDate(null));
    }

    @Test
    public void isValidExerciseDate() {
        // null birthday
        assertThrows(NullPointerException.class, () -> ExerciseDate.isValidExerciseDate(null));

        // invalid birthdays
        assertFalse(ExerciseDate.isValidExerciseDate("")); // empty string
        assertFalse(ExerciseDate.isValidExerciseDate(" ")); // spaces only
        assertFalse(ExerciseDate.isValidExerciseDate("91")); // random numbers
        assertFalse(ExerciseDate.isValidExerciseDate("not a birthday")); // non-numeric
        assertFalse(ExerciseDate.isValidExerciseDate("not a birthday1")); // non-numeric
        assertFalse(ExerciseDate.isValidExerciseDate("not a birthday2")); // non-numeric
        assertFalse(ExerciseDate.isValidExerciseDate("not a birthday3")); // non-numeric
        assertFalse(ExerciseDate.isValidExerciseDate("not a birthday4")); // non-numeric
        assertFalse(ExerciseDate.isValidExerciseDate("11-e33-1998")); // erroneous alphanumeric
        assertFalse(ExerciseDate.isValidExerciseDate("11/12/1213")); // incorrect delimiter
        assertFalse(ExerciseDate.isValidExerciseDate("11-13-1213")); // months more than 12
        assertFalse(ExerciseDate.isValidExerciseDate("32-11-1213")); // dates more than 31
        assertFalse(ExerciseDate.isValidExerciseDate("9-11-12131")); // years more than 4 digits
        assertFalse(ExerciseDate.isValidExerciseDate(
            DATE_NOW.plusDays(1).format(DATE_TIME_FORMATTER))); // 1 day after the current day
        assertFalse(ExerciseDate.isValidExerciseDate(
            DATE_NOW.minusYears(1).minusDays(1).format(DATE_TIME_FORMATTER))); // 1 year and 1 day before current date
        assertFalse(ExerciseDate.isValidExerciseDate(
            DATE_NOW.minusYears(2).format(DATE_TIME_FORMATTER))); // 2 years before current date

        // valid birthday
        assertTrue(ExerciseDate.isValidExerciseDate(
            DATE_NOW.format(DATE_TIME_FORMATTER))); // current date
        assertTrue(ExerciseDate.isValidExerciseDate(
            DATE_NOW.minusMonths(4).format(DATE_TIME_FORMATTER))); // 4 months before current date
        assertTrue(ExerciseDate.isValidExerciseDate(
            DATE_NOW.minusYears(1).format(DATE_TIME_FORMATTER))); // 1 year before current date

    }
}
