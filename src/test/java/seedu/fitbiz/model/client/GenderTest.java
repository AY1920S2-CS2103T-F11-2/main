package seedu.fitbiz.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = " ";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid genders
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("tranny"));
        assertFalse(Gender.isValidGender("shemale"));
        assertFalse(Gender.isValidGender("Other")); // match with Others
        assertFalse(Gender.isValidGender("23552")); // numeric
        assertFalse(Gender.isValidGender("MaleFemale")); // multiple
        assertFalse(Gender.isValidGender("Ma le")); // spaces within
        assertFalse(Gender.isValidGender("feMa3le"));

        // valid gender
        assertTrue(Gender.isValidGender("Male"));
        assertTrue(Gender.isValidGender("Female"));
        assertTrue(Gender.isValidGender("Others"));
        assertTrue(Gender.isValidGender("male"));
        assertTrue(Gender.isValidGender("feMale"));
        assertTrue(Gender.isValidGender("others"));

    }
}
