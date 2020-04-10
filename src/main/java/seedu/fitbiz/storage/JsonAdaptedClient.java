package seedu.fitbiz.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fitbiz.commons.exceptions.IllegalValueException;
import seedu.fitbiz.model.client.Address;
import seedu.fitbiz.model.client.Birthday;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.CurrentWeight;
import seedu.fitbiz.model.client.Email;
import seedu.fitbiz.model.client.Gender;
import seedu.fitbiz.model.client.Height;
import seedu.fitbiz.model.client.Name;
import seedu.fitbiz.model.client.PersonalBest;
import seedu.fitbiz.model.client.Phone;
import seedu.fitbiz.model.client.Remark;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.client.TargetWeight;
import seedu.fitbiz.model.exercise.UniqueExerciseList;
import seedu.fitbiz.model.schedule.ScheduleList;
import seedu.fitbiz.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String gender;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    private final String birthday;
    private final String height;
    private final String targetWeight;
    private final String currentWeight;
    private final String remark;
    private final List<JsonAdaptedSport> sports = new ArrayList<>();
    private final List<JsonAdaptedExercise> exerciseList = new ArrayList<>();
    private final List<JsonAdaptedSchedule> scheduleList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("gender") String gender,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("address") String address, @JsonProperty("birthday") String birthday,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("currentWeight") String currentWeight,
            @JsonProperty("targetWeight") String targetWeight, @JsonProperty("height") String height,
            @JsonProperty("remark") String remark, @JsonProperty("sports") List<JsonAdaptedSport> sports,
            @JsonProperty("exerciseList") List<JsonAdaptedExercise> exerciseList,
            @JsonProperty("scheduleList") List<JsonAdaptedSchedule> scheduleList) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.birthday = birthday;
        this.height = height;
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
        this.remark = remark;
        if (sports != null) {
            this.sports.addAll(sports);
        }
        if (exerciseList != null) {
            this.exerciseList.addAll(exerciseList);
        }
        if (scheduleList != null) {
            this.scheduleList.addAll(scheduleList);
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        gender = source.getGender().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        birthday = source.getBirthday().displayValue;
        height = source.getHeight().value;
        currentWeight = source.getCurrentWeight().value;
        targetWeight = source.getTargetWeight().value;
        remark = source.getRemark().value;
        sports.addAll(source.getSports().stream()
                .map(JsonAdaptedSport::new)
                .collect(Collectors.toList()));
        exerciseList.addAll(source.getExerciseList()
                .asUnmodifiableObservableList().stream()
                .map(JsonAdaptedExercise::new)
                .collect(Collectors.toList()));
        scheduleList.addAll(source.getScheduleList().getArrayList().stream()
                    .map(JsonAdaptedSchedule::new)
                    .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's
     * {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(clientTags);

        final List<Sport> clientSports = new ArrayList<>();
        for (JsonAdaptedSport sport : sports) {
            clientSports.add(sport.toModelType());
        }
        final Set<Sport> modelSport = new HashSet<>(clientSports);

        final UniqueExerciseList modelExerciseList = new UniqueExerciseList();
        for (JsonAdaptedExercise ex : exerciseList) {
            modelExerciseList.add(ex.toModelType());
        }
        modelExerciseList.sortByExerciseDateAndName(); // do a sanity presort to ensure initial list is sorted

        final PersonalBest modelPersonalBest = new PersonalBest();

        final ScheduleList modelScheduleList = new ScheduleList();
        for (JsonAdaptedSchedule schedule : scheduleList) {
            modelScheduleList.add(schedule.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (birthday == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidBirthday(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(height)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);

        if (currentWeight == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, CurrentWeight.class.getSimpleName()));
        }
        if (!CurrentWeight.isValidWeight(currentWeight)) {
            throw new IllegalValueException(CurrentWeight.MESSAGE_CONSTRAINTS);
        }
        final CurrentWeight modelCurrentWeight = new CurrentWeight(currentWeight);

        if (targetWeight == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TargetWeight.class.getSimpleName()));
        }
        if (!TargetWeight.isValidWeight(targetWeight)) {
            throw new IllegalValueException(TargetWeight.MESSAGE_CONSTRAINTS);
        }
        final TargetWeight modelTargetWeight = new TargetWeight(targetWeight);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        return new Client(modelName, modelGender, modelPhone, modelEmail, modelAddress, modelTags, modelBirthday,
                modelCurrentWeight, modelTargetWeight, modelHeight, modelRemark, modelSport, modelExerciseList,
                modelPersonalBest, modelScheduleList);
    }
}
