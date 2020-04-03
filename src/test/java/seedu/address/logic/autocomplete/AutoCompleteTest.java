package seedu.address.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import seedu.address.ui.ResultDisplay;

public class AutoCompleteTest {

    private static final String EMPTY_STRING = "";
    private static final String COMMANDS_WITH_WHITE_SPACES = "add ";
    private static final String INVALID_COMMAND = "zzzz";

    private static final String AMBIGUOUS_COMMAND_1 = "a";
    private static final String LONGEST_PREFIX_AMBIGUOUS_COMMAND_1 = "add-";
    private static final int CARET_POSITION_AMBIGUOUS_COMMAND_1 = 4;
    private static final String SIMILAR_COMMANDS_STRING_1 = "[add-e, add-c]";

    private static final String AMBIGUOUS_COMMAND_2 = "e";
    private static final String LONGEST_PREFIX_AMBIGUOUS_COMMAND_2 = "e";
    private static final int CARET_POSITION_AMBIGUOUS_COMMAND_2 = 1;
    private static final String SIMILAR_COMMANDS_STRING_2 = "[exit, edit-c]";

    private static final String UNAMBIGUOUS_COMMAND_1 = "ex";
    private static final String FULL_UNAMBIGUOUS_COMMAND_1 = "exit";
    private static final int CARET_POSITION_UNAMBIGUOUS_COMMAND_1 = 4;

    private static class ResultDisplayStub extends ResultDisplay {
        private String feedbackToUser;

        public String getFeedbackToUser() {
            return feedbackToUser;
        }

        public void clear() {
            setFeedbackToUser(EMPTY_STRING);
        }

        @Override
        public void setFeedbackToUser(String feedbackToUser) {
            this.feedbackToUser = feedbackToUser;
        }
    }

    private static TextField textField;
    private static ResultDisplayStub resultDisplayStub;
    private AutoComplete autoComplete;

    @BeforeAll
    private static void setUpAll() {
        // needed for JavaFx to start properly
        Platform.startup(() -> {
            textField = new TextField();
        });
        resultDisplayStub = new ResultDisplayStub();
    }

    @BeforeEach
    private void setUpEach() {
        textField.clear();
        resultDisplayStub.clear();
        autoComplete = new AutoComplete(textField, resultDisplayStub);
    }

    @Test
    public void constructor_nullFields_throwsNullPointer() {
        assertThrows(NullPointerException.class, () -> new AutoComplete(null, null));
    }

    @Test
    public void constructor_nonNullFields_noErrorsThrown() {
        assertDoesNotThrow(() -> new AutoComplete(textField, resultDisplayStub));
    }

    @Test
    public void execute_whiteSpaces_nothingChanged() {
        String resultDisplayBefore = resultDisplayStub.getFeedbackToUser();
        textField.setText(COMMANDS_WITH_WHITE_SPACES);
        int caretPositionBefore = textField.getCaretPosition();

        autoComplete.execute();

        String resultDisplayAfter = resultDisplayStub.getFeedbackToUser();
        String textFieldAfter = textField.getText();
        int caretPositionAfter = textField.getCaretPosition();

        assertEquals(resultDisplayAfter, resultDisplayBefore);
        assertEquals(textFieldAfter, COMMANDS_WITH_WHITE_SPACES);
        assertEquals(caretPositionAfter, caretPositionBefore);
    }

    @Test
    public void execute_invalidCommands_resultDisplayShowNoValidCommands() {
        textField.setText(INVALID_COMMAND);
        int caretPositionBefore = textField.getCaretPosition();

        autoComplete.execute();

        String resultDisplayAfter = resultDisplayStub.getFeedbackToUser();
        String textFieldAfter = textField.getText();
        int caretPositionAfter = textField.getCaretPosition();

        assertEquals(resultDisplayAfter, AutoComplete.FEEDBACK_NO_COMMANDS);
        assertEquals(textFieldAfter, INVALID_COMMAND);
        assertEquals(caretPositionAfter, caretPositionBefore);
    }

    @Test
    public void execute_unambiguousCommand_corretOutputs() {
        textField.setText(UNAMBIGUOUS_COMMAND_1);

        autoComplete.execute();

        String resultDisplayAfter = resultDisplayStub.getFeedbackToUser();
        String textFieldAfter = textField.getText();

        assertEquals(resultDisplayAfter, AutoComplete.FEEDBACK_EMPTY_STRING);
        assertEquals(textFieldAfter, FULL_UNAMBIGUOUS_COMMAND_1);
        assertEquals(textField.getCaretPosition(), CARET_POSITION_UNAMBIGUOUS_COMMAND_1);
    }

    @Test
    public void execute_ambiguousCommand1_orretOutputs() {
        textField.setText(AMBIGUOUS_COMMAND_1);

        autoComplete.execute();

        String resultDisplayAfter = resultDisplayStub.getFeedbackToUser();
        String textFieldAfter = textField.getText();

        assertEquals(resultDisplayAfter, AutoComplete.FEEDBACK_MULTIPLE_COMMANDS + SIMILAR_COMMANDS_STRING_1);
        assertEquals(textFieldAfter, LONGEST_PREFIX_AMBIGUOUS_COMMAND_1);
        assertEquals(textField.getCaretPosition(), CARET_POSITION_AMBIGUOUS_COMMAND_1);
    }

    @Test
    public void execute_ambiguousCommand2_orretOutputs() {
        textField.setText(AMBIGUOUS_COMMAND_2);

        autoComplete.execute();

        String resultDisplayAfter = resultDisplayStub.getFeedbackToUser();
        String textFieldAfter = textField.getText();

        assertEquals(resultDisplayAfter, AutoComplete.FEEDBACK_MULTIPLE_COMMANDS + SIMILAR_COMMANDS_STRING_2);
        assertEquals(textFieldAfter, LONGEST_PREFIX_AMBIGUOUS_COMMAND_2);
        assertEquals(textField.getCaretPosition(), CARET_POSITION_AMBIGUOUS_COMMAND_2);
    }

}
