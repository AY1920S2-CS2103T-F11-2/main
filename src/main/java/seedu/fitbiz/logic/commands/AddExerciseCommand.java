package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SETS;

import java.util.List;

import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.parser.Prefix;
import seedu.fitbiz.logic.statistics.PersonalBestFinder;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.UniqueExerciseList;

/**
 * Adds an exercise done by a client in FitBiz.
 */
public class AddExerciseCommand extends Command {

    public static final List<Prefix> PREFIXES = List.of(PREFIX_NAME,
            PREFIX_DATE, PREFIX_REPS, PREFIX_EXERCISE_WEIGHT, PREFIX_SETS);

    public static final String COMMAND_WORD = "add-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise done by a client in FitBiz. "
            + "Parameters: " + PREFIX_NAME + "EXERCISE_NAME " + PREFIX_DATE + "DATE " + "[" + PREFIX_REPS + "REPS] "
            + "[" + PREFIX_EXERCISE_WEIGHT + "EXERCISE_WEIGHT] " + "[" + PREFIX_SETS + "SETS]\n" + "Example: "
            + COMMAND_WORD + " " + PREFIX_NAME + "bench press " + PREFIX_DATE + "10-12-2020 " + PREFIX_REPS + "12 "
            + PREFIX_EXERCISE_WEIGHT + "50 " + PREFIX_SETS + "8";

    public static final String MESSAGE_SUCCESS = "New exercise recorded:\n%1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in this client. "
            + "Consider incrementing the sets of that existing exercise.";
    public static final String MESSAGE_CLIENT_NOT_IN_VIEW = "You currently do not have a client in view, "
            + "use the view-c command to view a client first";

    private final Exercise toAdd;

    /**
     * Creates an AddExerciseCommand to add the specified {@code Exercise}
     */
    public AddExerciseCommand(Exercise exercise) {
        requireNonNull(exercise);
        this.toAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasClientInView()) {
            throw new CommandException(MESSAGE_CLIENT_NOT_IN_VIEW);
        }

        Client clientToEdit = model.getClientInView();
        UniqueExerciseList clientToEditExerciseList = clientToEdit.getExerciseList();

        if (clientToEditExerciseList.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        Client editedClient = model.addExerciseToClient(toAdd);

        PersonalBestFinder.generateAndSetPersonalBest(editedClient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getForOutput()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExerciseCommand // instanceof handles nulls
                        && toAdd.equals(((AddExerciseCommand) other).toAdd));
    }
}
