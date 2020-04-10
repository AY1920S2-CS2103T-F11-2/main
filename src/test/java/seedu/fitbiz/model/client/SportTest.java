package seedu.fitbiz.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SportTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sport(null));
    }

    @Test
    public void constructor_invalidSport_throwsIllegalArgumentException() {
        String invalidSport = " ";
        assertThrows(IllegalArgumentException.class, () -> new Sport(invalidSport));
    }

    @Test
    public void isValidSport() {
        // null Sport
        assertThrows(NullPointerException.class, () -> Sport.isValidSport(null));

        // invalid sport
        assertFalse(Sport.isValidSport("-")); // a dash
        assertFalse(Sport.isValidSport(".")); // a period
        assertFalse(Sport.isValidSport("$$(*#(+_")); // weird symbols

        // valid sport
        assertTrue(Sport.isValidSport("NIL")); // no sport
        assertTrue(Sport.isValidSport("no sport")); // no sport
        assertTrue(Sport.isValidSport("hockey")); // one sport
        assertTrue(Sport.isValidSport("jengabuildingfreestyle81mountainskydivingswimveryfast")); // long sport
        assertTrue(Sport.isValidSport("81xtwelveriiasdfmnklanlcccoccunut")); // sport with numbers
        assertTrue(Sport.isValidSport("Cross country mountain biking")); // sport with space
    }

    @Test
    public void equals_validSport() {
        Sport s1 = new Sport("100000m race");

        assertTrue(s1.equals(s1));
        assertTrue(s1.equals(new Sport("100000m race")));

        assertFalse(s1.equals(new Sport("100000mrace")));
    }

    @Test
    public void hashCode_validSport() {
        Sport s1 = new Sport("100000m race");

        assertTrue(s1.hashCode() == new Sport("100000m race").hashCode());

        assertFalse(s1.hashCode() == new Sport("100000mrace").hashCode());
    }
}
