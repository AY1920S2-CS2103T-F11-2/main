package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SportsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sports(null));
    }

    @Test
    public void constructor_invalidSports_throwsIllegalArgumentException() {
        String invalidSports = "";
        assertThrows(IllegalArgumentException.class, () -> new Sports(invalidSports));
    }

    @Test
    public void isValidSports() {
        // null Sports
        assertThrows(NullPointerException.class, () -> Sports.isValidSports(null));

        // invalid sports
        assertFalse(Sports.isValidSports("")); // empty string
        assertFalse(Sports.isValidSports(" ")); // spaces only

        // valid sports
        assertTrue(Sports.isValidSports("-")); // no sports
        assertTrue(Sports.isValidSports(".")); // no sport
        assertTrue(Sports.isValidSports("NIL")); // no sports
        assertTrue(Sports.isValidSports("no sport")); // no sport
        assertTrue(Sports.isValidSports("hockey")); // one sport
        assertTrue(Sports.isValidSports("jengabuildingfreestyle81mountainskydivingswimveryfast")); // long sport
        assertTrue(Sports.isValidSports("81xtwelveriiasdfmnklanl cccoccunut")); // long weird sport
        assertTrue(Sports.isValidSports("Cross-country mountain biking")); // sports with "-" and numbers
    }

    @Test
    public void equals_validHeight() {
        Sports h1 = new Sports("100000m race");

        assertTrue(h1.equals(h1));
        assertTrue(h1.equals(new Sports("100000m race")));

        assertFalse(h1.equals(new Sports("100000mrace")));
    }

    @Test
    public void hashCode_validHeight() {
        Sports h1 = new Sports("100000m race");

        assertTrue(h1.hashCode() == new Sports("100000m race").hashCode());

        assertFalse(h1.hashCode() == new Sports("100000mrace").hashCode());
    }
}
