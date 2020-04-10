package seedu.fitbiz.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TargetWeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TargetWeight(null));
    }

    @Test
    public void constructor_invalidTargetWeight_throwsIllegalArgumentException() {
        String invalidTargetWeight = "i";
        assertThrows(IllegalArgumentException.class, () -> new TargetWeight(invalidTargetWeight));
    }

    @Test
    public void isValidWeight() {
        // null weight
        assertThrows(NullPointerException.class, () -> TargetWeight.isValidWeight(null));

        // invalid weight
        assertFalse(TargetWeight.isValidWeight(" ")); // spaces only
        assertFalse(TargetWeight.isValidWeight("ab")); // contains no numbers
        assertFalse(TargetWeight.isValidWeight("a.b")); // contains no numbers
        assertFalse(TargetWeight.isValidWeight(".2")); // no leading number before decimal
        assertFalse(TargetWeight.isValidWeight("2.2.2")); // too many decimal points
        assertFalse(TargetWeight.isValidWeight("-23")); // negative numbers
        assertFalse(TargetWeight.isValidWeight("-23.23")); // negative decimals
        assertFalse(TargetWeight.isValidWeight("as.23")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23.as")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23.a1")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23z1")); // inclusion of letters
        assertFalse(TargetWeight.isValidWeight("23,23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23|23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23/23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23./23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23/.23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("2-3.23")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("23.2'3")); // wrong symbols
        assertFalse(TargetWeight.isValidWeight("41.34573223138567385432")); // long decimal
        assertFalse(TargetWeight.isValidWeight("34573223138567385432.3")); // long decimal

        // valid weight
        assertTrue(TargetWeight.isValidWeight("")); // empty string
        assertTrue(TargetWeight.isValidWeight("0")); // single digit
        assertTrue(TargetWeight.isValidWeight("5")); // single digit
        assertTrue(TargetWeight.isValidWeight("003")); // leading zeros
        assertTrue(TargetWeight.isValidWeight("456")); // numbers only
        assertTrue(TargetWeight.isValidWeight("1.2")); // numbers with decimal points
        assertTrue(TargetWeight.isValidWeight("45.23")); // numbers with decimal points
        assertTrue(TargetWeight.isValidWeight("0.1")); // numbers with decimal points and leading 0
    }

    @Test
    public void formatWeight_properlyFormatted() {
        assertEquals((new TargetWeight("0")).value, "0.00"); // single digits
        assertEquals((new TargetWeight("5")).value, "5.00"); // single digits
        assertEquals((new TargetWeight("003")).value, "3.00"); // strip leading zeros
        assertEquals((new TargetWeight("000")).value, "0.00"); // at least one zero before decimal place
        assertEquals((new TargetWeight("000.3")).value, "0.30"); // at least one zero before decimal place
        assertEquals((new TargetWeight("45.23")).value, "45.23"); // at least one zero before decimal place
        assertEquals((new TargetWeight("")).value, ""); // empty string
    }

    @Test
    public void equals_validWeight() {
        TargetWeight w1 = new TargetWeight("12.2");

        assertTrue(w1.equals(w1));
        assertTrue(w1.equals(new TargetWeight("12.2")));

        assertFalse(w1.equals(new TargetWeight("12")));
    }

    @Test
    public void hashCode_validWeight() {
        TargetWeight w1 = new TargetWeight("12.2");

        assertTrue(w1.hashCode() == new TargetWeight("12.2").hashCode());

        assertFalse(w1.hashCode() == new TargetWeight("12.1").hashCode());
    }
}
