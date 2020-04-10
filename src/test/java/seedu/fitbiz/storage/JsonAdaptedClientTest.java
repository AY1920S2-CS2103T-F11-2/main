package seedu.fitbiz.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbiz.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.fitbiz.testutil.Assert.assertThrows;
import static seedu.fitbiz.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.exceptions.IllegalValueException;
import seedu.fitbiz.model.client.Address;
import seedu.fitbiz.model.client.Birthday;
import seedu.fitbiz.model.client.CurrentWeight;
import seedu.fitbiz.model.client.Email;
import seedu.fitbiz.model.client.Gender;
import seedu.fitbiz.model.client.Height;
import seedu.fitbiz.model.client.Name;
import seedu.fitbiz.model.client.Phone;
import seedu.fitbiz.model.client.Remark;
import seedu.fitbiz.model.client.TargetWeight;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_BIRTHDAY = "happy bday!";
    private static final String INVALID_CURRENT_WEIGHT = "eighty 2";
    private static final String INVALID_GENDER = "helicopter";
    private static final String INVALID_TARGET_WEIGHT = "tree fiddy";
    private static final String INVALID_HEIGHT = "fiddy 3";
    private static final String INVALID_SCHEDULE = "mon Time: 99:99 - 55:55";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final String VALID_CURRENT_WEIGHT = BENSON.getCurrentWeight().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_TARGET_WEIGHT = BENSON.getTargetWeight().toString();
    private static final String VALID_HEIGHT = BENSON.getHeight().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream().map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSport> VALID_SPORTS = BENSON.getSports().stream().map(JsonAdaptedSport::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedExercise> VALID_EXERCISE_LIST = BENSON.getExerciseList()
            .asUnmodifiableObservableList().stream().map(JsonAdaptedExercise::new).collect(Collectors.toList());
    private static final List<JsonAdaptedSchedule> VALID_SCHEDULE_LIST = BENSON.getScheduleList().getArrayList()
            .stream().map(JsonAdaptedSchedule::new).collect(Collectors.toList());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(INVALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, null, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, null,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, invalidTags, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

    @Test
    public void toModelType_invalidBirthday_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, INVALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Birthday.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullBirthday_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidCurrentWeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, INVALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = CurrentWeight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullCurrentWeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, null, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CurrentWeight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, INVALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTargetWeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, INVALID_TARGET_WEIGHT, VALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = TargetWeight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullTargetWeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, null, VALID_HEIGHT, VALID_REMARK,
                VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TargetWeight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, INVALID_HEIGHT,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, null,
                VALID_REMARK, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_CURRENT_WEIGHT, VALID_TARGET_WEIGHT, VALID_HEIGHT,
                null, VALID_SPORTS, VALID_EXERCISE_LIST, VALID_SCHEDULE_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }
}
