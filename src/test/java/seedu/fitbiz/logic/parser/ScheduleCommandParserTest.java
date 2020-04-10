package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.commands.CommandTestUtil.INVALID_DAY_DESC;
import static seedu.fitbiz.logic.commands.CommandTestUtil.INVALID_ENDTIME_DESC;
import static seedu.fitbiz.logic.commands.CommandTestUtil.INVALID_STARTTIME_DESC;
import static seedu.fitbiz.logic.commands.CommandTestUtil.SCHEDULE_DESC_MON;
import static seedu.fitbiz.logic.commands.CommandTestUtil.VALID_DAY_MON;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.fitbiz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbiz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.ScheduleCommand;
import seedu.fitbiz.model.schedule.Day;
import seedu.fitbiz.model.schedule.EndTime;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.StartTime;

public class ScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DAY_MON, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = targetIndex.getOneBased() + SCHEDULE_DESC_MON;

        ArrayList<Schedule> scheduleArrayList = new ArrayList<Schedule>();
        scheduleArrayList.add(new Schedule(new Day("mon"), new StartTime("1200"), new EndTime("1400")));
        ScheduleCommand expectedCommand = new ScheduleCommand(targetIndex, scheduleArrayList);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingOptionalFields_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = "" + targetIndex.getOneBased() + " " + PREFIX_SCHEDULE;

        ArrayList<Schedule> scheduleArrayList = new ArrayList<Schedule>();
        ScheduleCommand expectedCommand = new ScheduleCommand(targetIndex, scheduleArrayList);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        String message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1" + INVALID_DAY_DESC, message); // invalid day
        assertParseFailure(parser, "1" + INVALID_STARTTIME_DESC, message); // invalid start time
        assertParseFailure(parser, "1" + INVALID_ENDTIME_DESC, message); // invalid end time
    }
}
