package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.Model;

/**
 * Clears the FitBiz.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all data from FitBiz";
    public static final String MESSAGE_SUCCESS = "FitBiz has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFitBiz(new FitBiz());
        model.clearClientInView();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
