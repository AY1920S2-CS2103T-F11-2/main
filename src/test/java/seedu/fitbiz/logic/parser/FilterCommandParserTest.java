package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbiz.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.logic.commands.FilterCommand;
import seedu.fitbiz.model.client.TagAndSportContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTagAndSport_throwsParseException() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new TagAndSportContainsKeywordsPredicate(new ArrayList<String>(),
                        new ArrayList<String>()));
        assertParseSuccess(parser, "t/ s/", expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new TagAndSportContainsKeywordsPredicate(Arrays.asList("normal", "vegetarian"),
                        Arrays.asList("hockey", "sumo")));
        assertParseSuccess(parser, "t/normal vegetarian s/hockey sumo", expectedFilterCommand);
    }
}
