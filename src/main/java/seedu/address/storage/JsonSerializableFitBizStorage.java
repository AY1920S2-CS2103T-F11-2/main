package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FitBiz;
import seedu.address.model.ReadOnlyFitBiz;
import seedu.address.model.client.Client;

/**
 * An Immutable FitBiz that is serializable to JSON format.
 */
@JsonRootName(value = "fitbiz")
class JsonSerializableFitBizStorage {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFitBizStorage} with the given clients.
     */
    @JsonCreator
    public JsonSerializableFitBizStorage(@JsonProperty("clients") List<JsonAdaptedClient> clients) {
        this.clients.addAll(clients);
    }

    /**
     * Converts a given {@code ReadOnlyFitBiz} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFitBizStorage}.
     */
    public JsonSerializableFitBizStorage(ReadOnlyFitBiz source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this FitBiz into the model's {@code FitBiz} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FitBiz toModelType() throws IllegalValueException {
        FitBiz fitBiz = new FitBiz();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (fitBiz.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            fitBiz.addClient(client);
        }
        return fitBiz;
    }

}
