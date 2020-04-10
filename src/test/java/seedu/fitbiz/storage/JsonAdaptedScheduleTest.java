package seedu.fitbiz.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbiz.testutil.TypicalClients.BENSON;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.exceptions.IllegalValueException;

class JsonAdaptedScheduleTest {

    private static final String INVALID_DAY = "RANDOMDAY";
    private static final String INVALID_START_TIME = "5555";
    private static final String INVALID_END_TIME = "9999";

    private static final String VALID_DAY = "MON";
    private static final String VALID_START_TIME = "1100";
    private static final String VALID_END_TIME = "1200";

    @Test
    public void toModelType_validSchedule_returnsSchedule() {
        JsonAdaptedSchedule schedule = new JsonAdaptedSchedule(BENSON.getScheduleList().getArrayList().get(0));

        try {
            assertEquals(BENSON.getScheduleList().getArrayList().get(0), schedule.toModelType());
        } catch (IllegalValueException e) {
            throw new AssertionError("This test should not fail.");
        }
    }
}
