package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ExportUtil.DEFAULT_EXPORTS_DIRECTORY;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.util.ExportUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.exercise.Exercise;

/**
 * Exports the exercises of the current client in view defined in
 * {@code ClientInView}.
 */
public class ExportCommand extends Command {

    public static final String CSV_FILE_EXTENSION = ".csv";

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the exercises of the client in view.";

    public static final String MESSAGE_SUCCESS = "Succesfully exported this client's exercises to \"%s\".\n" + String
            .format("Please check your /%s folder, which should be located in the same directory as FitBiz.jar.",
                    DEFAULT_EXPORTS_DIRECTORY);

    public static final String MESSAGE_FAILED = "Failed to export this client's exercises. Please try again.";

    public static final String MESSAGE_CLIENT_NOT_IN_VIEW = "You currently do not have a client in view, "
            + "use the view-c command to view a client first";

    public static final String MESSAGE_NO_EXERCISES = "This client currently does not have any exercises to export.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasClientInView()) {
            throw new CommandException(MESSAGE_CLIENT_NOT_IN_VIEW);
        }

        Client clientInView = model.getClientInView();

        List<Exercise> exercises = clientInView.getExerciseList().asUnmodifiableObservableList();

        if (exercises.isEmpty()) {
            throw new CommandException(MESSAGE_NO_EXERCISES);
        }

        String fileName = clientInView.getName().fullName + CSV_FILE_EXTENSION;

        try {
            ExportUtil.exportExercisesAsCsv(exercises, fileName);
        } catch (IOException ex) {
            throw new CommandException(MESSAGE_FAILED);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }
}
