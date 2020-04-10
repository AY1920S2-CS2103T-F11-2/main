package seedu.fitbiz.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.fitbiz.commons.exceptions.IllegalValueException;
import seedu.fitbiz.model.client.Sport;

/**
 * Jackson-friendly version of {@link Sport}.
 */
class JsonAdaptedSport {

    private final String sportName;

    /**
     * Constructs a {@code JsonAdaptedSport} with the given {@code sportName}.
     */
    @JsonCreator
    public JsonAdaptedSport(String sportName) {
        this.sportName = sportName;
    }

    /**
     * Converts a given {@code Sport} into this class for Jackson use.
     */
    public JsonAdaptedSport(Sport source) {
        sportName = source.sportName;
    }

    @JsonValue
    public String getSportName() {
        return sportName;
    }

    /**
     * Converts this Jackson-friendly adapted sport object into the model's {@code Sport} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted sport.
     */
    public Sport toModelType() throws IllegalValueException {
        if (!Sport.isValidSport(sportName)) {
            throw new IllegalValueException(Sport.MESSAGE_CONSTRAINTS);
        }
        return new Sport(sportName);
    }

}
