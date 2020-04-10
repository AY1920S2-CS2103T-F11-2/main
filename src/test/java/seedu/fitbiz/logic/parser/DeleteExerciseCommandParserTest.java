package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fitbiz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fitbiz.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.logic.commands.DeleteExerciseCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteExerciseCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteExerciseCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteExerciseCommandParserTest {

    private DeleteExerciseCommandParser parser = new DeleteExerciseCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteExerciseCommand() {
        assertParseSuccess(parser, "1", new DeleteExerciseCommand(INDEX_FIRST_EXERCISE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExerciseCommand.MESSAGE_USAGE));
    }
}
