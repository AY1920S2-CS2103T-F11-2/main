package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.statistics.PersonalBestFinder;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.Client;

/**
 * Displays all details of a client from FitBiz, denoted by index in list view.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view-c";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View all available information of the client identified by the index number used in "
            + "the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Currently viewing: %1$s";

    private final Index index;

    public ViewCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client client = lastShownList.get(index.getZeroBased());
        model.setClientInView(client);

        PersonalBestFinder.generateAndSetPersonalBest(client);

        return new CommandResult(String.format(MESSAGE_SUCCESS, client.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        // state check
        ViewCommand e = (ViewCommand) other;
        return index.equals(e.index);
    }

}
