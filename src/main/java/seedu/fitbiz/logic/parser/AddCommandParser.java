package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_CURRENT_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_TARGET_WEIGHT;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.fitbiz.logic.commands.AddCommand;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
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
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    public static final String EMPTY_ATTRIBUTE = "";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_BIRTHDAY, PREFIX_CURRENT_WEIGHT, PREFIX_TARGET_WEIGHT,
                PREFIX_HEIGHT, PREFIX_REMARK, PREFIX_SPORT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME,
                PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Optional<String> genderString = argMultimap.getValue(PREFIX_GENDER);
        Gender gender = genderString.isPresent()
                ? ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get())
                : new Gender(EMPTY_ATTRIBUTE);
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        // start of optional attributes
        Optional<String> birthdayString = argMultimap.getValue(PREFIX_BIRTHDAY);
        Birthday birthday = birthdayString.isPresent()
                ? ParserUtil.parseBirthday(birthdayString.get())
                : new Birthday(EMPTY_ATTRIBUTE);
        Optional<String> heightString = argMultimap.getValue(PREFIX_HEIGHT);
        Height height = heightString.isPresent()
                ? ParserUtil.parseHeight(heightString.get())
                : new Height(EMPTY_ATTRIBUTE);
        Optional<String> currentWeightString = argMultimap.getValue(PREFIX_CURRENT_WEIGHT);
        CurrentWeight currentWeight = currentWeightString.isPresent()
                ? ParserUtil.parseCurrentWeight(currentWeightString.get())
                : new CurrentWeight(EMPTY_ATTRIBUTE);
        Optional<String> targetWeightString = argMultimap.getValue(PREFIX_TARGET_WEIGHT);
        TargetWeight targetWeight = targetWeightString.isPresent()
                ? ParserUtil.parseTargetWeight(targetWeightString.get())
                : new TargetWeight(EMPTY_ATTRIBUTE);
        Optional<String> remarkString = argMultimap.getValue(PREFIX_REMARK);
        Remark remark = remarkString.isPresent()
                ? ParserUtil.parseRemark(remarkString.get())
                : new Remark(EMPTY_ATTRIBUTE);
        Set<Sport> sportList = ParserUtil.parseSports(argMultimap.getAllValues(PREFIX_SPORT));
        UniqueExerciseList exerciseList = new UniqueExerciseList();
        PersonalBest personalBest = new PersonalBest();
        ScheduleList scheduleList = new ScheduleList();
        Client client = new Client(name, gender, phone, email, address, tagList, birthday,
                currentWeight, targetWeight, height, remark, sportList, exerciseList, personalBest,
                scheduleList);

        return new AddCommand(client);
    }

    /*
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
