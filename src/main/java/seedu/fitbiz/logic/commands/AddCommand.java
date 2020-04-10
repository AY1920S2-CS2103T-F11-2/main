package seedu.fitbiz.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_CURRENT_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SPORT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_TARGET_WEIGHT;

import java.util.List;

import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.parser.Prefix;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.Client;

/**
 * Adds a client to the FitBiz.
 */
public class AddCommand extends Command {

    public static final List<Prefix> PREFIXES = List.of(PREFIX_NAME,
            PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_GENDER,
            PREFIX_BIRTHDAY, PREFIX_CURRENT_WEIGHT, PREFIX_TARGET_WEIGHT,
            PREFIX_HEIGHT, PREFIX_REMARK, PREFIX_SPORT, PREFIX_TAG);

    public static final String COMMAND_WORD = "add-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to FitBiz. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_CURRENT_WEIGHT + "CURRENT_WEIGHT] "
            + "[" + PREFIX_TARGET_WEIGHT + "TARGET_WEIGHT] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_SPORT + "SPORT]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_GENDER + "Male "
            + PREFIX_BIRTHDAY + "26-01-1980 "
            + PREFIX_CURRENT_WEIGHT + "96 "
            + PREFIX_TARGET_WEIGHT + "69 "
            + PREFIX_HEIGHT + "156 "
            + PREFIX_SPORT + "Sumo Wrestling "
            + PREFIX_TAG + "Vegan "
            + PREFIX_TAG + "Lacks Protein "
            + PREFIX_REMARK + "History of back injuries";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This phone number and/or email already exists in FitBiz.\n"
            + "Clients must have different phone numbers and email addresses.";

    private final Client toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
