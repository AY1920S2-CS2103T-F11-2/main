package seedu.fitbiz.testutil;

import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.client.Client;

/**
 * A utility class to help with building {@code FitBiz} objects.
 * Example usage: <br>
 *     {@code FitBiz ab = new FitBizBuilder().withClient("John", "Doe").build();}
 */
public class FitBizBuilder {

    private FitBiz fitBiz;

    public FitBizBuilder() {
        fitBiz = new FitBiz();
    }

    public FitBizBuilder(FitBiz fitBiz) {
        this.fitBiz = fitBiz;
    }

    /**
     * Adds a new {@code Client} to the {@code FitBiz} that we are building.
     */
    public FitBizBuilder withClient(Client client) {
        fitBiz.addClient(client);
        return this;
    }

    public FitBiz build() {
        return fitBiz;
    }
}
