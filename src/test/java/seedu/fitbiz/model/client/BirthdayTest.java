package seedu.fitbiz.model.client;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class BirthdayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("91")); // random numbers
        assertFalse(Birthday.isValidBirthday("not a birthday")); // non-numeric
        assertFalse(Birthday.isValidBirthday("not a birthday1")); // non-numeric
        assertFalse(Birthday.isValidBirthday("not a birthday2")); // non-numeric
        assertFalse(Birthday.isValidBirthday("not a birthday3")); // non-numeric
        assertFalse(Birthday.isValidBirthday("not a birthday4")); // non-numeric
        assertFalse(Birthday.isValidBirthday("not a birthday5")); // non-numeric
        assertFalse(Birthday.isValidBirthday("11-e33-1998")); // erroneous alphanumeric
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plus(1, DAYS)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))); // birthday 1 day after current date
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plus(2, MONTHS)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))); //birthday 2 months after current date
        assertFalse(Birthday.isValidBirthday(LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))); // birthday today
        assertFalse(Birthday.isValidBirthday(LocalDate.now().minusYears(120).minusDays(1)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))); // birthday just the day before the earliest limit

        // valid birthday
        assertTrue(Birthday.isValidBirthday("01-01-1980")); // birthday in 01-01-1980
        assertTrue(Birthday.isValidBirthday("01-01-1970")); //birthday in 01-01-1979
        assertTrue(Birthday.isValidBirthday((""))); //default empty birthday
        assertTrue(Birthday.isValidBirthday(LocalDate.now().minusYears(120)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))); //birthday right on the earliest limit

        assertTrue(new Birthday("01-01-1980").hashCode() == new Birthday("01-01-1980").hashCode());
        assertTrue(new Birthday("01-01-1980").toString().equals("01-01-1980") == true);
    }
}
