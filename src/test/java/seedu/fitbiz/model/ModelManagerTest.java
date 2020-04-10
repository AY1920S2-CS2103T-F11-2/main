package seedu.fitbiz.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.fitbiz.testutil.Assert.assertThrows;
import static seedu.fitbiz.testutil.TypicalClients.ALICE;
import static seedu.fitbiz.testutil.TypicalClients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.commons.core.GuiSettings;
import seedu.fitbiz.model.client.NameContainsKeywordsPredicate;
import seedu.fitbiz.testutil.FitBizBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FitBiz(), new FitBiz(modelManager.getFitBiz()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFitBizFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFitBizFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFitBizFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFitBizFilePath(null));
    }

    @Test
    public void setFitBizFilePath_validPath_setsFitBizFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFitBizFilePath(path);
        assertEquals(path, modelManager.getFitBizFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInFitBiz_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInFitBiz_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void equals() {
        FitBiz fitBiz = new FitBizBuilder().withClient(ALICE).withClient(BENSON).build();
        FitBiz differentFitBiz = new FitBiz();
        UserPrefs userPrefs = new UserPrefs();
        ClientInView clientInView = new ClientInView();

        // same values -> returns true
        modelManager = new ModelManager(fitBiz, userPrefs, clientInView);
        ModelManager modelManagerCopy = new ModelManager(fitBiz, userPrefs, clientInView);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different fitBiz -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFitBiz, userPrefs, clientInView)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(fitBiz, userPrefs, clientInView)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFitBizFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(fitBiz, differentUserPrefs, clientInView)));
    }
}
