package seedu.fitbiz.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidHeight_throwsIllegalArgumentException() {
        String invalidHeight = "i";
        assertThrows(IllegalArgumentException.class, () -> new Height(invalidHeight));
    }

    @Test
    public void isValidHeight() {
        // null height
        assertThrows(NullPointerException.class, () -> Height.isValidHeight(null));

        // invalid height
        assertFalse(Height.isValidHeight(" ")); // spaces only
        assertFalse(Height.isValidHeight("ab")); // contains no numbers
        assertFalse(Height.isValidHeight("a.b")); // contains no numbers
        assertFalse(Height.isValidHeight(".2")); // no leading number before decimal
        assertFalse(Height.isValidHeight("2.2.2")); // too many decimal points
        assertFalse(Height.isValidHeight("-23")); // negative numbers
        assertFalse(Height.isValidHeight("-23.23")); // negative decimals
        assertFalse(Height.isValidHeight("as.23")); // inclusion of letters
        assertFalse(Height.isValidHeight("23.as")); // inclusion of letters
        assertFalse(Height.isValidHeight("23.a1")); // inclusion of letters
        assertFalse(Height.isValidHeight("23z1")); // inclusion of letters
        assertFalse(Height.isValidHeight("23,23")); // wrong symbols
        assertFalse(Height.isValidHeight("23|23")); // wrong symbols
        assertFalse(Height.isValidHeight("23/23")); // wrong symbols
        assertFalse(Height.isValidHeight("23./23")); // wrong symbols
        assertFalse(Height.isValidHeight("23/.23")); // wrong symbols
        assertFalse(Height.isValidHeight("2-3.23")); // wrong symbols
        assertFalse(Height.isValidHeight("23.2'3")); // wrong symbols
        assertFalse(Height.isValidHeight("41.34573223138567385432")); // long decimal
        assertFalse(Height.isValidHeight("34573223138567385432.3")); // long decimal

        // valid height
        assertTrue(Height.isValidHeight("")); // empty string
        assertTrue(Height.isValidHeight("0")); // single digit
        assertTrue(Height.isValidHeight("5")); // single digit
        assertTrue(Height.isValidHeight("003")); // leading zeros
        assertTrue(Height.isValidHeight("456")); // numbers only
        assertTrue(Height.isValidHeight("1.2")); // numbers with decimal points
        assertTrue(Height.isValidHeight("45.23")); // numbers with decimal points
        assertTrue(Height.isValidHeight("0.1")); // numbers with decimal points and leading 0
    }

    @Test
    public void equals_validHeight() {
        Height h1 = new Height("152.2");

        assertTrue(h1.equals(h1));
        assertTrue(h1.equals(new Height("152.2")));

        assertFalse(h1.equals(new Height("152")));
    }

    @Test
    public void formatHeight_properlyFormatted() {
        assertEquals((new Height("0")).value, "0.00"); // single digits
        assertEquals((new Height("5")).value, "5.00"); // single digits
        assertEquals((new Height("003")).value, "3.00"); // strip leading zeros
        assertEquals((new Height("000")).value, "0.00"); // at least one zero before decimal place
        assertEquals((new Height("000.3")).value, "0.30"); // at least one zero before decimal place
        assertEquals((new Height("45.23")).value, "45.23"); // at least one zero before decimal place
        assertEquals((new Height("")).value, ""); // empty string
    }

    @Test
    public void hashCode_validHeight() {
        Height h1 = new Height("152.2");

        assertTrue(h1.hashCode() == new Height("152.2").hashCode());

        assertFalse(h1.hashCode() == new Height("152.1").hashCode());
    }
}
