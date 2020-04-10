package seedu.fitbiz.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.fitbiz.logic.autocomplete.AutoComplete;
import seedu.fitbiz.logic.commands.CommandResult;
import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.logic.history.CommandHistory;
import seedu.fitbiz.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error-text-field";
    public static final String SUCCESS_STYLE_CLASS = "success-text-field";
    private static final String FXML = "CommandBox.fxml";
    // A generous estimate for the length of the user input string
    private static final int CARET_POSITION_INDEX = Integer.MAX_VALUE;

    private final CommandExecutor commandExecutor;
    private final CommandHistory commandHistory;
    private final AutoComplete autoComplete;

    @FXML
    private TextField commandTextField;

    public CommandBox(CommandExecutor commandExecutor, ResultDisplay resultDisplay) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandHistory = new CommandHistory();
        autoComplete = new AutoComplete(commandTextField, resultDisplay);

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        // handles the up and down arrow keys for command history
        handleUpDownArrowKeys();

        // handles the tab key for auto complete
        handleTabKey();
    }

    /**
     * Handles the "up" and "down" arrow key for the command history.
     */
    private void handleUpDownArrowKeys() {
        commandTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.UP) {
                    String prevCommand = commandHistory.getPreviousCommand();
                    commandTextField.setText(prevCommand);
                    commandTextField.positionCaret(CARET_POSITION_INDEX);
                } else if (ke.getCode() == KeyCode.DOWN) {
                    String nextCommand = commandHistory.getNextCommand();
                    commandTextField.setText(nextCommand);
                    commandTextField.positionCaret(CARET_POSITION_INDEX);
                }
            }
        });
    }

    /**
     * Handles the "tab" key for the command autocompletion.
     */
    private void handleTabKey() {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.TAB) {
                ke.consume();
                autoComplete.execute();
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String enteredCommand = commandTextField.getText();
        commandHistory.addToHistory(enteredCommand);
        try {
            commandTextField.setText("");
            commandExecutor.execute(enteredCommand);
            setStyleToIndicateCommandStatus(SUCCESS_STYLE_CLASS);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandStatus(ERROR_STYLE_CLASS);
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
        }

        if (styleClass.contains(SUCCESS_STYLE_CLASS)) {
            commandTextField.getStyleClass().remove(SUCCESS_STYLE_CLASS);
        }
    }

    /**
     * Sets the command box to {@code style} to indicate command status.
     */
    private void setStyleToIndicateCommandStatus(String style) {
        setStyleToDefault(); // clear all status styles first

        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(style)) {
            return;
        }

        styleClass.add(style);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.fitbiz.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
