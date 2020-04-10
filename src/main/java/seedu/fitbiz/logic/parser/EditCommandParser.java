package seedu.fitbiz.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.logic.commands.EditCommand;
import seedu.fitbiz.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbiz.logic.parser.exceptions.ParseException;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY, PREFIX_CURRENT_WEIGHT, PREFIX_TARGET_WEIGHT,
                        PREFIX_HEIGHT, PREFIX_REMARK, PREFIX_SPORT);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editClientDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editClientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editClientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            editClientDescriptor.setBirthday(ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()));
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            editClientDescriptor.setHeight(ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_CURRENT_WEIGHT).isPresent()) {
            editClientDescriptor.setCurrentWeight(ParserUtil.parseCurrentWeight(argMultimap
                    .getValue(PREFIX_CURRENT_WEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_TARGET_WEIGHT).isPresent()) {
            editClientDescriptor.setTargetWeight(ParserUtil.parseTargetWeight(argMultimap
                    .getValue(PREFIX_TARGET_WEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editClientDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editClientDescriptor::setTags);
        parseSportForEdit(argMultimap.getAllValues(PREFIX_SPORT)).ifPresent(editClientDescriptor::setSports);
        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editClientDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> sports} into a {@code Set<Sport>} if {@code sports} is non-empty.
     * If {@code sports} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Sport>} containing zero sports.
     */
    private Optional<Set<Sport>> parseSportForEdit(Collection<String> sport) throws ParseException {
        assert sport != null;

        if (sport.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> sportSet = sport.size() == 1 && sport.contains("") ? Collections.emptySet() : sport;
        return Optional.of(ParserUtil.parseSports(sportSet));
    }

}
