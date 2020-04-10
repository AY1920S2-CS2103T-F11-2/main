package seedu.fitbiz.model;

import seedu.fitbiz.model.client.Client;

/**
 * This represents the model of the client currently in view.
 */
public class ClientInView {

    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean hasClientInView() {
        return client != null;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ClientInView)) {
            return false;
        }

        // state check
        return (((ClientInView) obj).client == null && client == null)
            || ((ClientInView) obj).client.equals(client);
    }
}
