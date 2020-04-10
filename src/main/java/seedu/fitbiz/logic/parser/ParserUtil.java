package seedu.fitbiz.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.commons.util.StringUtil;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
import seedu.fitbiz.model.client.Address;
import seedu.fitbiz.model.client.Birthday;
import seedu.fitbiz.model.client.CurrentWeight;
import seedu.fitbiz.model.client.Email;
import seedu.fitbiz.model.client.Gender;
import seedu.fitbiz.model.client.Height;
import seedu.fitbiz.model.client.Name;
import seedu.fitbiz.model.client.Phone;
import seedu.fitbiz.model.client.Remark;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.client.TargetWeight;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;
import seedu.fitbiz.model.schedule.Day;
import seedu.fitbiz.model.schedule.EndTime;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.StartTime;
import seedu.fitbiz.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String EMPTY_ATTRIBUTE = "";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    private static final int DAY_INDEX = 0;
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 2;

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        String trimmedGenderInLowercase = trimmedGender.toLowerCase();
        switch (trimmedGenderInLowercase) {
        case "male":
        case "m":
            return new Gender("Male");
        case "female":
        case "f":
            return new Gender("Female");
        case "others":
        case "o":
            return new Gender("Others");
        default:
            return new Gender(EMPTY_ATTRIBUTE);
        }
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            if (tagName.equals(EMPTY_ATTRIBUTE)) {
                continue;
            }
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String birthday} into a {@code Birthday}.
     * Only birth dates earlier than the current date are allowed.
     *
     * @throws ParseException
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String height} into an {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code height} is invalid.
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        String trimmedHeight = height.trim();
        if (!Height.isValidHeight(trimmedHeight)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(trimmedHeight);
    }

    /**
     * Parses a {@code String current weight} into a {@code CurrentWeight}.
     *
     * @throws ParseException
     */
    public static CurrentWeight parseCurrentWeight(String currentWeight) throws ParseException {
        requireNonNull(currentWeight);
        String trimmedCurrentWeight = currentWeight.trim();
        if (!CurrentWeight.isValidWeight(trimmedCurrentWeight)) {
            throw new ParseException(CurrentWeight.MESSAGE_CONSTRAINTS);
        }
        return new CurrentWeight(trimmedCurrentWeight);
    }

    /**
     * Parses a {@code String target weight} into a {@code TargetWeight}.
     *
     * @throws ParseException
     */
    public static TargetWeight parseTargetWeight(String targetWeight) throws ParseException {
        requireNonNull(targetWeight);
        String trimmedTargetWeight = targetWeight.trim();
        if (!TargetWeight.isValidWeight(trimmedTargetWeight)) {
            throw new ParseException(TargetWeight.MESSAGE_CONSTRAINTS);
        }
        return new TargetWeight(trimmedTargetWeight);
    }

    /**
     * Parses a {@code String sport} into a {@code Sport}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sport} is invalid.
     */
    public static Sport parseSport(String sport) throws ParseException {
        requireNonNull(sport);
        String trimmedSport = sport.trim();
        if (!Sport.isValidSport(trimmedSport)) {
            throw new ParseException(Sport.MESSAGE_CONSTRAINTS);
        }
        return new Sport(trimmedSport);
    }

    /**
     * Parses {@code Collection<String> sports} into a {@code Set<Sport>}.
     */
    public static Set<Sport> parseSports(Collection<String> sports) throws ParseException {
        requireNonNull(sports);
        final Set<Sport> sportSet = new HashSet<>();
        for (String sportName : sports) {
            if (sportName.equals(EMPTY_ATTRIBUTE)) {
                continue;
            }
            sportSet.add(parseSport(sportName));
        }
        return sportSet;
    }

    /**
     * Parses a {@code String exercise name} into an {@code ExerciseName}. Leading
     * and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code exercise name} is invalid.
     */
    public static ExerciseName parseExerciseName(String exerciseName) throws ParseException {
        requireNonNull(exerciseName);
        String trimmedExerciseName = exerciseName.trim();
        if (!ExerciseName.isValidExerciseName(trimmedExerciseName)) {
            throw new ParseException(ExerciseName.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseName(trimmedExerciseName);
    }

    /**
     * Parses a {@code String reps} into an {@code ExerciseReps}. Leading and
     * trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reps} is invalid.
     */
    public static ExerciseReps parseExerciseReps(String reps) throws ParseException {
        requireNonNull(reps);
        String trimmedReps = reps.trim();
        if (!ExerciseReps.isValidExerciseReps(trimmedReps)) {
            throw new ParseException(ExerciseReps.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseReps(trimmedReps);
    }

    /**
     * Parses a {@code String sets} into an {@code ExerciseSets}. Leading and
     * trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sets} is invalid.
     */
    public static ExerciseSets parseExerciseSets(String sets) throws ParseException {
        requireNonNull(sets);
        String trimmedSets = sets.trim();
        if (!ExerciseSets.isValidExerciseSets(trimmedSets)) {
            throw new ParseException(ExerciseSets.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseSets(trimmedSets);
    }

    /**
     * Parses a {@code String exercise weight} into an {@code ExerciseWeight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code exercise weight} is invalid.
     */
    public static ExerciseWeight parseExerciseWeight(String exerciseWeight) throws ParseException {
        requireNonNull(exerciseWeight);
        String trimmedExerciseWeight = exerciseWeight.trim();
        if (!ExerciseWeight.isValidExerciseWeight(trimmedExerciseWeight)) {
            throw new ParseException(ExerciseWeight.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseWeight(trimmedExerciseWeight);
    }

    /**
     * Parses a {@code String date} into an {@code ExerciseDate}. Leading and
     * trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static ExerciseDate parseExerciseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!ExerciseDate.isValidExerciseDate(trimmedDate)) {
            throw new ParseException(ExerciseDate.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseDate(date);
    }

    /**
     * Parses a {@code List<String> days} into a {@code ArrayList<Day> dayList}.
     * Only correct day of the week is allowed
     *
     * @throws ParseException
     */
    public static ArrayList<Day> parseDay(List<String> days) throws ParseException {
        requireNonNull(days);
        ArrayList<Day> dayList = new ArrayList<Day>();
        for (String day : days) {
            String trimmedDay = day.trim();
            if (!Day.isValidDay(trimmedDay)) {
                throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
            }
            dayList.add(new Day(trimmedDay));
        }
        return dayList;
    }

    /**
     * Parses a {@code List<String> time} into a {@code ArrayList<StartTime> startTimeList}.
     * Only time in 24 hour format is allowed.
     *
     * @throws ParseException
     */
    public static ArrayList<StartTime> parseStartTime(List<String> time) throws ParseException {
        requireNonNull(time);
        ArrayList<StartTime> startTimeList = new ArrayList<StartTime>();
        for (String startTime : time) {
            String trimmedTime = startTime.trim();
            if (!StartTime.isValidTimingFormat(trimmedTime)) {
                throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
            }
            startTimeList.add(new StartTime(trimmedTime));
        }
        return startTimeList;
    }

    /**
     * Parses a {@code List<String> time} into a {@code ArrayList<EndTime> endTimeList}.
     * Only time in 24 hour format is allowed.
     *
     * @throws ParseException
     */
    public static ArrayList<EndTime> parseEndTime(List<String> time) throws ParseException {
        requireNonNull(time);
        ArrayList<EndTime> endTimeList = new ArrayList<EndTime>();
        for (String endTime : time) {
            String trimmedTime = endTime.trim();
            if (!StartTime.isValidTimingFormat(trimmedTime)) {
                throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
            }
            endTimeList.add(new EndTime(trimmedTime));
        }
        return endTimeList;
    }

    /**
     * @author @Dban1
     * takes in the raw input string entered into the CLI from the user and processes it into an ArrayList
     * of String arrays for further parsing by the day, start and end time parsers respectively.
     */
    public static ArrayList<HashMap<String, String>> parseRawScheduleInput(List<String> input) throws ParseException {
        requireNonNull(input);
        ArrayList<HashMap<String, String>> returnList = new ArrayList<>();

        // short-circuit if empty input
        if (input.get(0).length() == 0) {
            return returnList;
        }

        try {
            for (String s : input) {
                HashMap<String, String> inputHash = new HashMap<>();
                String[] tripleData = s.split("-");
                inputHash.put("day", tripleData[DAY_INDEX]);
                inputHash.put("start", tripleData[START_INDEX]);
                inputHash.put("end", tripleData[END_INDEX]);

                returnList.add(inputHash);
            }
            return returnList;
        } catch (Exception e) {
            throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
        }
    }

}
