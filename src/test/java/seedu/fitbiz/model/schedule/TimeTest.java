package seedu.fitbiz.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TimeTest {

    private Time time = new Time("0000") {}; //anonymous class that extends abstract Time for testing

    @Test
    void isValidTimingFormat() {
        //null initialisation
        assertFalse(Time.isValidTimingFormat(null));

        //invalid timings
        assertFalse(Time.isValidTimingFormat("")); //empty strings
        assertFalse(Time.isValidTimingFormat("fr3u9f")); //random strings
        assertFalse(Time.isValidTimingFormat("4372")); //wrong 4 digit number

        //valid timings
        assertTrue(Time.isValidTimingFormat("0000"));
        assertTrue(Time.isValidTimingFormat("2359"));
        assertTrue(Time.isValidTimingFormat("0110"));
    }

    @Test
    void formatTime() {
        assertTrue(Time.formatTime("0000").equals("00:00"));
    }

    @Test
    void getTime() {
        assertTrue(time.getTime().equals("0000"));
    }

    @Test
    void testToString() {
        assertTrue(time.toString().equals("00:00"));
    }

    @Test
    void testEquals() {
        Time secondTime = new Time("0000") {};
        Time thirdTime = new Time("0001") {};

        assertTrue(time.equals(secondTime));
        assertFalse(time.equals(thirdTime));
    }

    @Test
    void getDirectTimeInt() {
        assertTrue(time.getDirectTimeInt() == 0);
    }

}
