package seedu.fitbiz.logic.commands;

import seedu.fitbiz.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String USER_GUIDE_URL = "https://ay1920s2-cs2103t-f11-2.github.io/main/UserGuide.html";

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redirects to FitBiz user guide";

    public static final String SHOWING_HELP_MESSAGE = "You should have been redirected to the FitBiz user guide"
            + " website. If not, please visit the following URL:\n"
            + USER_GUIDE_URL;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
