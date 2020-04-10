package seedu.fitbiz.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.fitbiz.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.fitbiz.testutil.Assert.assertThrows;
import static seedu.fitbiz.testutil.TypicalClients.ALICE;
import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.exceptions.DuplicateClientException;
import seedu.fitbiz.testutil.ClientBuilder;

public class FitBizTest {

    private final FitBiz fitBiz = new FitBiz();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fitBiz.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitBiz.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFitBiz_replacesData() {
        FitBiz newData = getTypicalFitBiz();
        fitBiz.resetData(newData);
        assertEquals(newData, fitBiz);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        FitBizStub newData = new FitBizStub(newClients);

        assertThrows(DuplicateClientException.class, () -> fitBiz.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitBiz.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInFitBiz_returnsFalse() {
        assertFalse(fitBiz.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInFitBiz_returnsTrue() {
        fitBiz.addClient(ALICE);
        assertTrue(fitBiz.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInFitBiz_returnsTrue() {
        fitBiz.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(fitBiz.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fitBiz.getClientList().remove(0));
    }

    /**
     * A stub FitBizStub whose clients list can violate interface constraints.
     */
    private static class FitBizStub implements ReadOnlyFitBiz {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        FitBizStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }
    }

}
