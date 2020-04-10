package seedu.fitbiz.model.schedule;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DayEnumTest {
    @Test
    public void valueOfTest() {
        assertTrue(DayEnum.Weekday.valueOf("MON") == DayEnum.Weekday.MON);
    }
}
