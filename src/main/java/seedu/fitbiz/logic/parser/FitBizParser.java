package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.fitbiz.logic.commands.AddCommand;
import seedu.fitbiz.logic.commands.AddExerciseCommand;
import seedu.fitbiz.logic.commands.ClearCommand;
import seedu.fitbiz.logic.commands.Command;
import seedu.fitbiz.logic.commands.DeleteCommand;
import seedu.fitbiz.logic.commands.DeleteExerciseCommand;
import seedu.fitbiz.logic.commands.EditCommand;
import seedu.fitbiz.logic.commands.EditExerciseCommand;
import seedu.fitbiz.logic.commands.ExitCommand;
import seedu.fitbiz.logic.commands.ExportCommand;
import seedu.fitbiz.logic.commands.FilterCommand;
import seedu.fitbiz.logic.commands.FindCommand;
import seedu.fitbiz.logic.commands.HelpCommand;
import seedu.fitbiz.logic.commands.ListCommand;
import seedu.fitbiz.logic.commands.ScheduleCommand;
import seedu.fitbiz.logic.commands.ViewCommand;
import seedu.fitbiz.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FitBizParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddExerciseCommand.COMMAND_WORD:
            return new AddExerciseCommandParser().parse(arguments);

        case EditExerciseCommand.COMMAND_WORD:
            return new EditExerciseCommandParser().parse(arguments);

        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);

        case DeleteExerciseCommand.COMMAND_WORD:
            return new DeleteExerciseCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
