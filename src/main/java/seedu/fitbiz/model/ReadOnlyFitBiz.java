package seedu.fitbiz.model;

import javafx.collections.ObservableList;
import seedu.fitbiz.model.client.Client;

/**
 * Unmodifiable view of FitBiz.
 */
public interface ReadOnlyFitBiz {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

}
