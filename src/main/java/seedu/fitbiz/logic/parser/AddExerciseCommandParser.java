package seedu.fitbiz.logic.parser;

import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SETS;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.fitbiz.logic.commands.AddExerciseCommand;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;

/**
 * Parses input arguments and creates a new AddExerciseCommand object
 */
public class AddExerciseCommandParser implements Parser<AddExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddExerciseCommand and returns an AddExerciseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExerciseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_REPS,
            PREFIX_EXERCISE_WEIGHT, PREFIX_SETS, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExerciseCommand.MESSAGE_USAGE));
        }

        ExerciseName name = ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get());

        Optional<String> repsString = argMultimap.getValue(PREFIX_REPS);
        ExerciseReps reps = repsString.isPresent()
            ? ParserUtil.parseExerciseReps(argMultimap.getValue(PREFIX_REPS).get())
            : new ExerciseReps("");

        Optional<String> exerciseWeightString = argMultimap.getValue(PREFIX_EXERCISE_WEIGHT);
        ExerciseWeight exerciseWeight = exerciseWeightString.isPresent()
            ? ParserUtil.parseExerciseWeight(argMultimap.getValue(PREFIX_EXERCISE_WEIGHT).get())
            : new ExerciseWeight("");

        Optional<String> setsString = argMultimap.getValue(PREFIX_SETS);
        ExerciseSets sets = setsString.isPresent()
            ? ParserUtil.parseExerciseSets(argMultimap.getValue(PREFIX_SETS).get())
            : new ExerciseSets("");

        ExerciseDate date = ParserUtil.parseExerciseDate(argMultimap.getValue(PREFIX_DATE).get());
        Exercise exercise = new Exercise(name, reps, sets, exerciseWeight, date);

        return new AddExerciseCommand(exercise);
    }

    /*
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
