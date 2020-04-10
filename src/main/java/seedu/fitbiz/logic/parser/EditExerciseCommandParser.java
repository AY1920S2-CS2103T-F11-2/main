package seedu.fitbiz.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SETS;

import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.EditExerciseCommand;
import seedu.fitbiz.logic.commands.EditExerciseCommand.EditExerciseDescriptor;
import seedu.fitbiz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditExerciseCommand object
 */
public class EditExerciseCommandParser implements Parser<EditExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditExerciseCommand and returns an EditExerciseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditExerciseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
            args, PREFIX_NAME, PREFIX_REPS, PREFIX_SETS, PREFIX_EXERCISE_WEIGHT, PREFIX_DATE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExerciseCommand.MESSAGE_USAGE), pe);
        }

        EditExerciseCommand.EditExerciseDescriptor editExerciseDescriptor = new EditExerciseDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editExerciseDescriptor.setExerciseName(
                ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_REPS).isPresent()) {
            editExerciseDescriptor.setExerciseReps(
                ParserUtil.parseExerciseReps(argMultimap.getValue(PREFIX_REPS).get()));
        }
        if (argMultimap.getValue(PREFIX_SETS).isPresent()) {
            editExerciseDescriptor.setExerciseSets(
                ParserUtil.parseExerciseSets(argMultimap.getValue(PREFIX_SETS).get()));
        }
        if (argMultimap.getValue(PREFIX_EXERCISE_WEIGHT).isPresent()) {
            editExerciseDescriptor.setExerciseWeight(
                ParserUtil.parseExerciseWeight(argMultimap.getValue(PREFIX_EXERCISE_WEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editExerciseDescriptor.setExerciseDate(
                ParserUtil.parseExerciseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (!editExerciseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditExerciseCommand.MESSAGE_NOT_EDITED);
        }

        return new EditExerciseCommand(index, editExerciseDescriptor);
    }

}
