package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.fitbiz.model.Model;

/**
 * Lists all clients in FitBiz to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list-c";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all clients in FitBiz";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
