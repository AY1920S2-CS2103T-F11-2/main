package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.logic.export.Exporter.DEFAULT_EXERCISES_DIRECTORY;

import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.export.Exporter;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.Client;

/**
 * Exports the exercises of the current client in view defined in
 * {@code ClientInView}.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the exercises of the client in view.";

    public static final String MESSAGE_SUCCESS = "Succesfully exported this client's exercises to \"%s\".\n"
            + String.format("Please check your /%s directory.", DEFAULT_EXERCISES_DIRECTORY);

    public static final String MESSAGE_CLIENT_NOT_IN_VIEW = "You currently do not have a client in view, "
            + "use the view-c command to view a client first";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasClientInView()) {
            throw new CommandException(MESSAGE_CLIENT_NOT_IN_VIEW);
        }

        Client clientInView = model.getClientInView();

        String fileName = Exporter.exportExercisesAsCsv(clientInView);

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }
}
