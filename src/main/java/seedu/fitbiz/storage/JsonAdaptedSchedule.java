package seedu.fitbiz.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fitbiz.commons.exceptions.IllegalValueException;
import seedu.fitbiz.model.schedule.Day;
import seedu.fitbiz.model.schedule.EndTime;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.StartTime;

/**
 * Jackson-friendly version of {@link Schedule}.
 */
class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule %s field is missing!";

    private final String day;
    private final String startTime;
    private final String endTime;
    private final String clientName;

    /**
     * Constructs a {@code JsonAdaptedSchedule} with the given {@code Schedule}
     * details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("day") String day, @JsonProperty("startTime") String startTime,
            @JsonProperty("endTime") String endTime, @JsonProperty("clientName") String clientName) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientName = clientName;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        day = source.getDay().value.toString();
        startTime = source.getStartTime().getTime();
        endTime = source.getEndTime().getTime();
        clientName = source.getClientName();
    }

    /**
     * Converts this Jackson-friendly adapted schedule object into the model's
     * {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted schedule.
     */
    public Schedule toModelType() throws IllegalValueException {
        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = new Day(day);

        if (startTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName()));
        }
        if (!StartTime.isValidTimingFormat(startTime)) {
            throw new IllegalValueException(StartTime.MESSAGE_CONSTRAINTS);
        }
        final StartTime modelStartTime = new StartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName()));
        }
        if (!EndTime.isValidTimingFormat(endTime)) {
            throw new IllegalValueException(EndTime.MESSAGE_CONSTRAINTS);
        }
        final EndTime modelEndTime = new EndTime(endTime);

        if (clientName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }
        final String modelClientName = clientName;

        Schedule schedule = new Schedule(modelDay, modelStartTime, modelEndTime);
        schedule.assignClientName(modelClientName);

        return schedule;
    }

}
