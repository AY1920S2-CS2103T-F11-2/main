package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.DeleteExerciseCommand;
import seedu.fitbiz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExerciseCommand object
 *
 * @author @yonggiee
 */
public class DeleteExerciseCommandParser implements Parser<DeleteExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteExerciseCommand and returns a DeleteExerciseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExerciseCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteExerciseCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteExerciseCommand.MESSAGE_USAGE), pe);
        }
    }

}
