package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.logic.parser.Prefix;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.TagAndSportContainsKeywordsPredicate;

/**
 * @author tohkerwei
 * Filters and lists all clients in FitBiz whose tag or sports contains argument keywords for the respective parameters.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final List<Prefix> PREFIXES = List.of(PREFIX_TAG, PREFIX_SPORT);

    public static final String COMMAND_WORD = "filter-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters and display all clients whose tags or sports "
            + "contains any of the specified keywords for respective parameter (case-insensitive) \n"
            + "Parameters: t/KEYWORD... s/KEYWORD...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "normal" + " " + PREFIX_SPORT + "hockey";

    private final TagAndSportContainsKeywordsPredicate predicate;

    public FilterCommand(TagAndSportContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}

