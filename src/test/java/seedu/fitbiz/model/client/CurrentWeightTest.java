package seedu.fitbiz.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentWeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentWeight(null));
    }

    @Test
    public void constructor_invalidCurrentWeight_throwsIllegalArgumentException() {
        String invalidCurrentWeight = "i";
        assertThrows(IllegalArgumentException.class, () -> new CurrentWeight(invalidCurrentWeight));
    }

    @Test
    public void isValidWeight() {
        // null weight
        assertThrows(NullPointerException.class, () -> CurrentWeight.isValidWeight(null));

        // invalid weight
        assertFalse(CurrentWeight.isValidWeight(" ")); // spaces only
        assertFalse(CurrentWeight.isValidWeight("ab")); // contains no numbers
        assertFalse(CurrentWeight.isValidWeight("a.b")); // contains no numbers
        assertFalse(CurrentWeight.isValidWeight(".2")); // no leading number before decimal
        assertFalse(CurrentWeight.isValidWeight("2.2.2")); // too many decimal points
        assertFalse(CurrentWeight.isValidWeight("-23")); // negative numbers
        assertFalse(CurrentWeight.isValidWeight("-23.23")); // negative decimals
        assertFalse(CurrentWeight.isValidWeight("as.23")); // inclusion of letters
        assertFalse(CurrentWeight.isValidWeight("23.as")); // inclusion of letters
        assertFalse(CurrentWeight.isValidWeight("23.a1")); // inclusion of letters
        assertFalse(CurrentWeight.isValidWeight("23z1")); // inclusion of letters
        assertFalse(CurrentWeight.isValidWeight("23,23")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("23|23")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("23/23")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("23./23")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("23/.23")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("2-3.23")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("23.2'3")); // wrong symbols
        assertFalse(CurrentWeight.isValidWeight("41.34573223138567385432")); // long decimal
        assertFalse(CurrentWeight.isValidWeight("34573223138567385432.3")); // long decimal

        // valid weight
        assertTrue(CurrentWeight.isValidWeight("")); // empty string
        assertTrue(CurrentWeight.isValidWeight("0")); // single digit
        assertTrue(CurrentWeight.isValidWeight("5")); // single digit
        assertTrue(CurrentWeight.isValidWeight("003")); // leading zeros
        assertTrue(CurrentWeight.isValidWeight("456")); // numbers only
        assertTrue(CurrentWeight.isValidWeight("1.2")); // numbers with decimal points
        assertTrue(CurrentWeight.isValidWeight("45.23")); // numbers with decimal points
        assertTrue(CurrentWeight.isValidWeight("0.1")); // numbers with decimal points and leading 0
    }

    @Test
    public void formatWeight_properlyFormatted() {
        assertEquals((new TargetWeight("0")).value, "0.00"); // single digits
        assertEquals((new TargetWeight("5")).value, "5.00"); // single digits
        assertEquals((new TargetWeight("003")).value, "3.00"); // strip leading zeros
        assertEquals((new TargetWeight("000")).value, "0.00"); // at least one zero before decimal place
        assertEquals((new TargetWeight("000.3")).value, "0.30"); // at least one zero before decimal place
        assertEquals((new TargetWeight("45.23")).value, "45.23"); // at least one zero before decimal place
    }

    @Test
    public void equals_validWeight() {
        CurrentWeight w1 = new CurrentWeight("12.2");

        assertTrue(w1.equals(w1));
        assertTrue(w1.equals(new CurrentWeight("12.2")));

        assertFalse(w1.equals(new CurrentWeight("12")));
    }

    @Test
    public void hashCode_validWeight() {
        CurrentWeight w1 = new CurrentWeight("12.2");

        assertTrue(w1.hashCode() == new CurrentWeight("12.2").hashCode());

        assertFalse(w1.hashCode() == new CurrentWeight("12.1").hashCode());
    }
}
