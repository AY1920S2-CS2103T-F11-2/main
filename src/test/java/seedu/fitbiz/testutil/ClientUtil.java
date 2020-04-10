package seedu.fitbiz.testutil;

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

import java.util.Set;

import seedu.fitbiz.logic.commands.AddCommand;
import seedu.fitbiz.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.tag.Tag;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Client client) {
        return AddCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().value + " ");
        client.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getBirthday().ifPresent(bday -> sb.append(PREFIX_BIRTHDAY).append(bday.displayValue).append(" "));
        descriptor.getCurrentWeight().ifPresent(cWeight -> sb.append(PREFIX_CURRENT_WEIGHT)
                .append(cWeight.value).append(" "));
        descriptor.getGender().ifPresent(gender -> sb.append(PREFIX_GENDER).append(gender.value).append(" "));
        descriptor.getHeight().ifPresent(height -> sb.append(PREFIX_HEIGHT).append(height.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        descriptor.getTargetWeight().ifPresent(tWeight -> sb.append(PREFIX_TARGET_WEIGHT)
                .append(tWeight.value).append(" "));
        if (descriptor.getSports().isPresent()) {
            Set<Sport> sports = descriptor.getSports().get();
            if (sports.isEmpty()) {
                sb.append(PREFIX_SPORT).append(" ");
            } else {
                sports.forEach(s -> sb.append(PREFIX_SPORT).append(s.sportName).append(" "));
            }
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
