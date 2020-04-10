package seedu.fitbiz.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.fitbiz.logic.commands.EditCommand;
import seedu.fitbiz.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbiz.model.client.Address;
import seedu.fitbiz.model.client.Birthday;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.CurrentWeight;
import seedu.fitbiz.model.client.Email;
import seedu.fitbiz.model.client.Gender;
import seedu.fitbiz.model.client.Height;
import seedu.fitbiz.model.client.Name;
import seedu.fitbiz.model.client.Phone;
import seedu.fitbiz.model.client.Remark;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.client.TargetWeight;
import seedu.fitbiz.model.tag.Tag;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditCommand.EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing
     * {@code client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditCommand.EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
        descriptor.setTags(client.getTags());
        descriptor.setGender(client.getGender());
        descriptor.setCurrentWeight(client.getCurrentWeight());
        descriptor.setTargetWeight(client.getTargetWeight());
        descriptor.setRemark(client.getRemark());
        descriptor.setSports(client.getSports());
        descriptor.setBirthday(client.getBirthday());
        descriptor.setHeight(client.getHeight());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code CurrentWeight} of the {@code EditClientDescriptor} that we
     * are building.
     */
    public EditClientDescriptorBuilder withCurrentWeight(String currentWeight) {
        descriptor.setCurrentWeight(new CurrentWeight(currentWeight));
        return this;
    }

    /**
     * Sets the {@code TargetWeight} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withTargetWeight(String targetWeight) {
        descriptor.setTargetWeight(new TargetWeight(targetWeight));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withBirthday(String birthday) {
        descriptor.setBirthday(new Birthday(birthday));
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code EditClientDescriptor} that we are
     * building.
     */
    public EditClientDescriptorBuilder withHeight(String height) {
        descriptor.setHeight(new Height(height));
        return this;
    }

    /**
     * Parses the {@code sports} into a {@code Set<Sport>} and set it to the
     * {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withSports(String... sports) {
        Set<Sport> tagSet = Stream.of(sports).map(Sport::new).collect(Collectors.toSet());
        descriptor.setSports(tagSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditClientDescriptor build() {
        return descriptor;
    }
}
