package seedu.fitbiz.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DayTest {

    private Day originalDay = new Day("mon");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }


    @Test
    public void isValidDay() {
        //null day
        assertThrows(NullPointerException.class, () -> Day.isValidDay(null));

        //invalid days
        assertFalse(Day.isValidDay("")); //empty String
        assertFalse(Day.isValidDay("bs289sb")); //random String 1
        assertFalse(Day.isValidDay("D#$D#..")); //random String 2
        assertFalse(Day.isValidDay("monday")); //not adhering to three-letter format
        assertFalse(Day.isValidDay("kim")); //random String 3

        //valid days
        assertTrue(Day.isValidDay("mon")); //monday
        assertTrue(Day.isValidDay("mOn")); //case insensitive
        assertTrue(Day.isValidDay("tuE")); //case insensitive
        assertTrue(Day.isValidDay("Wed")); //case insensitive
        assertTrue(Day.isValidDay("tHu")); //case insensitive
        assertTrue(Day.isValidDay("frI")); //case insensitive
        assertTrue(Day.isValidDay("SAT")); //case insensitive
        assertTrue(Day.isValidDay("sUN")); //case insensitive
    }

    @Test
    public void equals() {
        Day clonedDay = originalDay.clone();
        assertTrue(originalDay.equals(clonedDay));
    }

    @Test
    public void toStringTest() {
        System.out.println(originalDay.toString());
        assertTrue(originalDay.toString().equals("MON"));
    }

    @Test
    void getDayEnum() {
        assertTrue(originalDay.getDayEnum().equals(DayEnum.Weekday.MON));
    }
}
