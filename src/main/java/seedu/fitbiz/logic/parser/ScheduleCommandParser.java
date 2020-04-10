package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.ScheduleCommand;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
import seedu.fitbiz.model.schedule.Day;
import seedu.fitbiz.model.schedule.EndTime;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.StartTime;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /*
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * @author Dban1
     * Checks for any overlaps in given schedule with all existing schedules in input set.
     */
    private static boolean checkIfOverlaps(Schedule schedule, TreeSet<Schedule> set) {
        if (set.size() == 0) {
            set.add(schedule);
            return true;
        }
        return set.add(schedule);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SCHEDULE);
        Index index;

        if (argMultimap.getPreamble().isEmpty()
                || !argMultimap.getValue(PREFIX_SCHEDULE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        // Creates an ArrayList of HashMap, each HashMap contains 3 input attributes day, start time, end time.
        ArrayList<HashMap<String, String>> processedInput = ParserUtil.parseRawScheduleInput(
                argMultimap.getAllValues(PREFIX_SCHEDULE));

        // Process the input attributes into their respective ArrayList<String>
        ArrayList<String> dayStringList = new ArrayList<>();
        ArrayList<String> startListString = new ArrayList<>();
        ArrayList<String> endListString = new ArrayList<>();

        for (HashMap<String, String> h: processedInput) {
            dayStringList.add(h.get("day"));
            startListString.add(h.get("start"));
            endListString.add(h.get("end"));
        }

        // Passes in the respective ArrayList<String> for the input attributes to their parser functions to obtain
        // their respective ArrayLists.
        ArrayList<Day> dayList = ParserUtil.parseDay(dayStringList);
        ArrayList<StartTime> startTimeList = ParserUtil.parseStartTime(startListString);
        ArrayList<EndTime> endTimeList = ParserUtil.parseEndTime(endListString);

        ArrayList<Schedule> scheduleList = new ArrayList<>();

        // Checks if there are the same number of arguments for Day, StartTime and EndTime.
        if (dayList.size() != startTimeList.size() || dayList.size() != endTimeList.size()) {
            String invalidCountMsg = String.format(ScheduleCommand.MESSAGE_INVALID_ARG_COUNT, dayList.size(),
                    startTimeList.size(), endTimeList.size());
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, invalidCountMsg));
        }

        // Checks for overlapping schedules.
        TreeSet<Schedule> scheduleSet = new TreeSet<>();
        for (int i = 0; i < dayList.size(); i++) {
            Day day = dayList.get(i);
            StartTime startTime = startTimeList.get(i);
            EndTime endTime = endTimeList.get(i);
            Schedule schedule;
            try {
                schedule = new Schedule(day, startTime, endTime);
            } catch (IllegalArgumentException iae) {
                throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
            }
            if (!checkIfOverlaps(schedule, scheduleSet)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ScheduleCommand.MESSAGE_CONTAINS_DUPLICATES));
            }
            scheduleList.add(schedule);
        }

        return new ScheduleCommand(index, scheduleList);
    }
}
