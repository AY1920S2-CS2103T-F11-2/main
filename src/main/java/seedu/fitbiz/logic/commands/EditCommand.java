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
import static seedu.fitbiz.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.fitbiz.commons.core.Messages;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.commons.util.CollectionUtil;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.client.Address;
import seedu.fitbiz.model.client.Birthday;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.CurrentWeight;
import seedu.fitbiz.model.client.Email;
import seedu.fitbiz.model.client.Gender;
import seedu.fitbiz.model.client.Height;
import seedu.fitbiz.model.client.Name;
import seedu.fitbiz.model.client.PersonalBest;
import seedu.fitbiz.model.client.Phone;
import seedu.fitbiz.model.client.Remark;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.client.TargetWeight;
import seedu.fitbiz.model.exercise.UniqueExerciseList;
import seedu.fitbiz.model.schedule.ScheduleList;
import seedu.fitbiz.model.tag.Tag;

/**
 * Edits the details of an existing client in the FitBiz.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the index number used in the displayed client list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_CURRENT_WEIGHT + "CURRENT_WEIGHT] "
            + "[" + PREFIX_TARGET_WEIGHT + "TARGET_WEIGHT] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_SPORT + "SPORT]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_BIRTHDAY + "26-01-1980"
            + PREFIX_HEIGHT + "156";

    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This phone number and/or email already exists in FitBiz.\n"
            + "Clients must have different phone numbers and email addresses.";

    private final Index index;
    private final EditClientDescriptor editClientDescriptor;

    /**
     * @param index                of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditCommand(Index index, EditClientDescriptor editClientDescriptor) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Gender updatedGender = editClientDescriptor.getGender().orElse(clientToEdit.getGender());
        Phone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Email updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        Set<Tag> updatedTags = editClientDescriptor.getTags().orElse(clientToEdit.getTags());
        Set<Sport> updatedSport = editClientDescriptor.getSports().orElse(clientToEdit.getSports());
        Birthday updatedBirthday = editClientDescriptor.getBirthday().orElse(clientToEdit.getBirthday());
        Height updatedHeight = editClientDescriptor.getHeight().orElse(clientToEdit.getHeight());
        CurrentWeight updatedCurrentWeight = editClientDescriptor.getCurrentWeight()
                .orElse(clientToEdit.getCurrentWeight());
        TargetWeight updatedTargetWeight = editClientDescriptor.getTargetWeight()
                .orElse(clientToEdit.getTargetWeight());
        Remark updatedRemark = editClientDescriptor.getRemark()
                .orElse(clientToEdit.getRemark());
        UniqueExerciseList exerciseList = clientToEdit.getExerciseList();
        PersonalBest personalBest = clientToEdit.getPersonalBest();
        ScheduleList scheduleList = clientToEdit.getScheduleList();
        return new Client(updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedBirthday, updatedCurrentWeight, updatedTargetWeight, updatedHeight, updatedRemark, updatedSport,
                exerciseList, personalBest, scheduleList);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setClient(clientToEdit, editedClient);
        model.updateClientViewIfApplicable(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index) && editClientDescriptor.equals(e.editClientDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will
     * replace the corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Gender gender;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Birthday birthday;
        private Height height;
        private CurrentWeight currentWeight;
        private TargetWeight targetWeight;
        private Set<Sport> sport;
        private Remark remark;
        public EditClientDescriptor() {}

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setGender(toCopy.gender);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setBirthday(toCopy.birthday);
            setHeight(toCopy.height);
            setCurrentWeight(toCopy.currentWeight);
            setTargetWeight(toCopy.targetWeight);
            setSports(toCopy.sport);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, gender, phone, email, address, tags, birthday,
                    currentWeight, targetWeight, height, sport, remark);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Birthday> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public void setBirthday(Birthday birthday) {
            this.birthday = birthday;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<CurrentWeight> getCurrentWeight() {
            return Optional.ofNullable(currentWeight);
        }

        public void setCurrentWeight(CurrentWeight currentWeight) {
            this.currentWeight = currentWeight;
        }

        public Optional<TargetWeight> getTargetWeight() {
            return Optional.ofNullable(targetWeight);
        }

        public void setTargetWeight(TargetWeight targetWeight) {
            this.targetWeight = targetWeight;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        /**
         * Returns an unmodifiable sport set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code sports} is null.
         */
        public Optional<Set<Sport>> getSports() {
            return (sport != null) ? Optional.of(Collections.unmodifiableSet(sport)) : Optional.empty();
        }

        /**
         * Sets {@code sports} to this object's {@code sports}. A defensive copy of
         * {@code sports} is used internally.
         */
        public void setSports(Set<Sport> sport) {
            this.sport = (sport != null) ? new HashSet<>(sport) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of
         * {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getGender().equals(e.getGender())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getBirthday().equals(e.getBirthday())
                    && getHeight().equals(e.getHeight())
                    && getCurrentWeight().equals(e.getCurrentWeight())
                    && getTargetWeight().equals(e.getTargetWeight())
                    && getRemark().equals(e.getRemark())
                    && getSports().equals(e.getSports());
        }
    }
}
