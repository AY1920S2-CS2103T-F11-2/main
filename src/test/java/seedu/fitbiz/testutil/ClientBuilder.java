package seedu.fitbiz.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.UniqueExerciseList;
import seedu.fitbiz.model.schedule.ScheduleList;
import seedu.fitbiz.model.tag.Tag;
import seedu.fitbiz.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "01-01-1992";
    public static final String DEFAULT_CURRENT_WEIGHT = "52";
    public static final String DEFAULT_TARGET_WEIGHT = "52";
    public static final String DEFAULT_GENDER = "Male";
    public static final String DEFAULT_HEIGHT = "175";
    public static final String DEFAULT_REMARK = "Some remarks";
    public static final String DEFAULT_SPORT = "Coding";
    public static final String DEFAULT_SCHEDULE = "mon Time: 12:00 - 14:00";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Birthday birthday;
    private CurrentWeight currentWeight;
    private Gender gender;
    private Height height;
    private Remark remark;
    private Set<Sport> sports;
    private TargetWeight targetWeight;
    // TODO: change this later
    private UniqueExerciseList exerciseList = new UniqueExerciseList();
    private PersonalBest personalBest = new PersonalBest();
    private ScheduleList scheduleList;

    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        currentWeight = new CurrentWeight(DEFAULT_CURRENT_WEIGHT);
        gender = new Gender(DEFAULT_GENDER);
        height = new Height(DEFAULT_HEIGHT);
        remark = new Remark(DEFAULT_REMARK);
        targetWeight = new TargetWeight(DEFAULT_TARGET_WEIGHT);
        tags = new HashSet<>();
        sports = new HashSet<>();
        scheduleList = new ScheduleList();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        birthday = clientToCopy.getBirthday();
        currentWeight = clientToCopy.getCurrentWeight();
        gender = clientToCopy.getGender();
        height = clientToCopy.getHeight();
        remark = clientToCopy.getRemark();
        targetWeight = clientToCopy.getTargetWeight();
        sports = new HashSet<>(clientToCopy.getSports());
        tags = new HashSet<>(clientToCopy.getTags());
        ScheduleList newScheduleList = clientToCopy.getScheduleList();
        scheduleList = newScheduleList;
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Client} that we are building.
     */
    public ClientBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code CurrentWeight} of the {@code Client} that we are building.
     */
    public ClientBuilder withCurrentWeight(String currentWeight) {
        this.currentWeight = new CurrentWeight(currentWeight);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Client} that we are building.
     */
    public ClientBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Client} that we are building.
     */
    public ClientBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Client} that we are building.
     */
    public ClientBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code TargetWeight} of the {@code Client} that we are building.
     */
    public ClientBuilder withTargetWeight(String targetWeight) {
        this.targetWeight = new TargetWeight(targetWeight);
        return this;
    }

    /**
     * Parses the {@code sports} into a {@code Set<Sport>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withSports(String... sports) {
        this.sports = SampleDataUtil.getSportSet(sports);
        return this;
    }

    /**
     * Adds the {@code exercise} to {@code UniqueExerciseList}.
     */
    public ClientBuilder withExercisesInExerciseList(Exercise exercise) {
        this.exerciseList.add(exercise);
        return this;
    }

    /**
     * Sets the {@code scheduleList} to the {@code Client} that we are building.
     */
    public ClientBuilder withScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
        return this;
    }

    /**
     * Builds and returns the client.
     */
    public Client build() {
        return new Client(name, gender, phone, email, address, tags, birthday, currentWeight,
                targetWeight, height, remark, sports, exerciseList, personalBest, scheduleList);
    }

}
