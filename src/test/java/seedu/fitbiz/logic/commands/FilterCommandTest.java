package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;

import static seedu.fitbiz.testutil.TypicalClients.getTypicalFitBiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.model.ClientInView;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.UserPrefs;
import seedu.fitbiz.model.client.TagAndSportContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());
    private Model expectedModel = new ModelManager(getTypicalFitBiz(), new UserPrefs(), new ClientInView());

    @Test
    public void equals() {
        TagAndSportContainsKeywordsPredicate firstPredicate =
                new TagAndSportContainsKeywordsPredicate(Collections.singletonList("firsttag"),
                        Collections.singletonList("firstsport"));
        TagAndSportContainsKeywordsPredicate secondPredicate =
                new TagAndSportContainsKeywordsPredicate(Collections.singletonList("secondtag"),
                        Collections.singletonList("secondsport"));

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_validTagAndSport_clientFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 1);
        TagAndSportContainsKeywordsPredicate predicate = preparePredicate("normal",
                "dance");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTagNoSport_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 2);
        TagAndSportContainsKeywordsPredicate predicate = preparePredicate("normal",
                "");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSportNoTag_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 1);
        TagAndSportContainsKeywordsPredicate predicate = preparePredicate("",
                "dance");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code tagInput and @code sportInput} into a {@code TagAndSportContainsKeywordsPredicate}.
     */
    private TagAndSportContainsKeywordsPredicate preparePredicate(String tagInput, String sportInput) {
        List<String> tags = new ArrayList<>();
        List<String> sports = new ArrayList<>();
        if (!tagInput.equals("")) {
            tags = Arrays.asList(tagInput.split("\\s+"));
        }

        if (!sportInput.equals("")) {
            sports = Arrays.asList(sportInput.split("\\s+"));
        }
        return new TagAndSportContainsKeywordsPredicate(tags, sports);
    }


}
