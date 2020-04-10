package seedu.fitbiz.logic.autocomplete;

import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_DELIMITTER;

import java.util.List;

import javafx.scene.control.TextField;
import seedu.fitbiz.commons.trie.SimilarWordsResult;
import seedu.fitbiz.commons.trie.Trie;
import seedu.fitbiz.logic.commands.AddCommand;
import seedu.fitbiz.logic.commands.AddExerciseCommand;
import seedu.fitbiz.logic.commands.ClearCommand;
import seedu.fitbiz.logic.commands.DeleteCommand;
import seedu.fitbiz.logic.commands.DeleteExerciseCommand;
import seedu.fitbiz.logic.commands.EditCommand;
import seedu.fitbiz.logic.commands.EditExerciseCommand;
import seedu.fitbiz.logic.commands.ExitCommand;
import seedu.fitbiz.logic.commands.ExportCommand;
import seedu.fitbiz.logic.commands.FindCommand;
import seedu.fitbiz.logic.commands.HelpCommand;
import seedu.fitbiz.logic.commands.ListCommand;
import seedu.fitbiz.logic.commands.ScheduleCommand;
import seedu.fitbiz.logic.commands.ViewCommand;
import seedu.fitbiz.logic.parser.Prefix;
import seedu.fitbiz.ui.ResultDisplay;

/**
 * This class contains the logic behind the autocomplete feature. It makes use
 * of {@code Trie} as the underlying data structure.
 */
public class AutoComplete {

    public static final String FEEDBACK_EMPTY_STRING = "";
    public static final String FEEDBACK_MULTIPLE_COMMANDS = "Commands found:\n";
    public static final String FEEDBACK_NO_COMMANDS = "No commands found";

    private static final int CARET_POSITION_INDEX = Integer.MAX_VALUE;
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE_STRING = " ";
    private static final String PREAMBLE_WHITE_SPACE = " ";

    private final Trie trie;
    private final TextField commandTextField;
    private final ResultDisplay resultDisplay;

    /**
     * Default constructor for this class. Note that both {@code commandTextField}
     * and {@code resultDisplay} must not be {@code null}.
     */
    public AutoComplete(TextField commandTextField, ResultDisplay resultDisplay) {
        requireAllNonNull(commandTextField, resultDisplay);
        trie = new Trie();
        this.commandTextField = commandTextField;
        this.resultDisplay = resultDisplay;
        addAllCommands();
    }

    /**
     * Adds all the commands included in FitBiz to {@code Trie}.
     */
    private void addAllCommands() {
        trie.insert(AddCommand.COMMAND_WORD);
        trie.insert(AddExerciseCommand.COMMAND_WORD);
        trie.insert(ClearCommand.COMMAND_WORD);
        trie.insert(DeleteCommand.COMMAND_WORD);
        trie.insert(DeleteExerciseCommand.COMMAND_WORD);
        trie.insert(EditCommand.COMMAND_WORD);
        trie.insert(EditExerciseCommand.COMMAND_WORD);
        trie.insert(ExitCommand.COMMAND_WORD);
        trie.insert(ExportCommand.COMMAND_WORD);
        trie.insert(FindCommand.COMMAND_WORD);
        trie.insert(HelpCommand.COMMAND_WORD);
        trie.insert(ListCommand.COMMAND_WORD);
        trie.insert(ScheduleCommand.COMMAND_WORD);
        trie.insert(ViewCommand.COMMAND_WORD);
    }

    /**
     * Returns a string of {@code prefixes} delimited by a single empty space.
     */
    private String generatePrefixesString(List<Prefix> prefixes) {
        String toReturn = EMPTY_STRING;
        for (Prefix p : prefixes) {
            toReturn += WHITE_SPACE_STRING + p.toString();
        }
        return toReturn;
    }

    private void noCommandHandler() {
        resultDisplay.setFeedbackToUser(FEEDBACK_NO_COMMANDS);
    }

    /**
     * Handles the instance when the autocomplete can uniquely identify a single
     * command.
     *
     * <p>
     * This method will auto generate the prefixes for some commands like add-c,
     * add-e, and schedule. The caret position will also be set to the most
     * appropriate position. The display of {@code ResultDisplay} will also indicate
     * the usage of the current completed command.
     * </p>
     */
    private void singleCommandHandler(String command) {
        String textToSet = command;
        String textToFeedback = FEEDBACK_EMPTY_STRING;
        int caretPositionToSet = CARET_POSITION_INDEX;

        switch (command) {
        case AddCommand.COMMAND_WORD:
            textToSet += generatePrefixesString(AddCommand.PREFIXES);
            textToFeedback = AddCommand.MESSAGE_USAGE;
            caretPositionToSet = textToSet.indexOf(PREFIX_DELIMITTER) + 1;
            break;
        case AddExerciseCommand.COMMAND_WORD:
            textToSet += generatePrefixesString(AddExerciseCommand.PREFIXES);
            textToFeedback = AddExerciseCommand.MESSAGE_USAGE;
            caretPositionToSet = textToSet.indexOf(PREFIX_DELIMITTER) + 1;
            break;
        case ClearCommand.COMMAND_WORD:
            textToFeedback = ClearCommand.MESSAGE_USAGE;
            break;
        case DeleteCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE;
            textToFeedback = DeleteCommand.MESSAGE_USAGE;
            break;
        case DeleteExerciseCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE;
            textToFeedback = DeleteExerciseCommand.MESSAGE_USAGE;
            break;
        case EditCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE;
            textToFeedback = EditCommand.MESSAGE_USAGE;
            break;
        case ExitCommand.COMMAND_WORD:
            textToFeedback = ExitCommand.MESSAGE_USAGE;
            break;
        case ExportCommand.COMMAND_WORD:
            textToFeedback = ExportCommand.MESSAGE_USAGE;
            break;
        case EditExerciseCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE;
            textToFeedback = EditExerciseCommand.MESSAGE_USAGE;
            break;
        case FindCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE;
            textToFeedback = FindCommand.MESSAGE_USAGE;
            break;
        case HelpCommand.COMMAND_WORD:
            textToFeedback = HelpCommand.MESSAGE_USAGE;
            break;
        case ListCommand.COMMAND_WORD:
            textToFeedback = ListCommand.MESSAGE_USAGE;
            break;
        case ScheduleCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE + generatePrefixesString(ScheduleCommand.PREFIXES);
            textToFeedback = ScheduleCommand.MESSAGE_USAGE;
            caretPositionToSet = textToSet.indexOf(PREAMBLE_WHITE_SPACE) + 1;
            break;
        case ViewCommand.COMMAND_WORD:
            textToSet += PREAMBLE_WHITE_SPACE;
            textToFeedback = ViewCommand.MESSAGE_USAGE;
            break;
        default:
            break;
        }

        commandTextField.setText(textToSet);
        commandTextField.positionCaret(caretPositionToSet);
        resultDisplay.setFeedbackToUser(textToFeedback);
    }

    /**
     * Handles the instance when the autocomplete cannot uniquely identify a single
     * command.
     */
    private void multipleCommandsHandler(SimilarWordsResult result) {
        commandTextField.setText(result.getLongestPrefix());
        commandTextField.positionCaret(CARET_POSITION_INDEX);
        resultDisplay.setFeedbackToUser(FEEDBACK_MULTIPLE_COMMANDS + result.getSimilarWords().toString());
    }

    /**
     * Handles the instance when the command has already been completed and the user
     * presses tab to get to the next prefix.
     *
     * <p>
     * This method will set the caret position of the user to the next
     * {@code PREFIX_DELIMITTER} (with wraparound) when the user presses tab. If no
     * such {@code PREFIX_DELIMITTER} exists in the user's command, this method will
     * stop.
     * </p>
     */
    private void completedCommandHandler(String currentCommand) {
        if (!currentCommand.contains(PREFIX_DELIMITTER)) {
            return;
        }

        int currentCaretPosition = commandTextField.getCaretPosition();
        int nextPrefixPosition = currentCommand.indexOf(PREFIX_DELIMITTER, currentCaretPosition);
        if (nextPrefixPosition == -1) {
            // next prefix not found, wrap around to start
            nextPrefixPosition = currentCommand.indexOf(PREFIX_DELIMITTER) + 1;
        } else {
            nextPrefixPosition++;
        }
        commandTextField.positionCaret(nextPrefixPosition);
    }

    /**
     * Executes the main logic behind the autocomplete, and should be called when
     * the user presses "tab".
     */
    public void execute() {
        String trimmedCommand = commandTextField.getText().trim();

        // command word has already been completed
        if (trimmedCommand.contains(WHITE_SPACE_STRING)) {
            completedCommandHandler(trimmedCommand);
            return;
        }

        SimilarWordsResult similarWords = trie.listAllSimilarWords(trimmedCommand);

        if (similarWords.hasNoResult()) {
            noCommandHandler();
        } else if (similarWords.hasOnlyOneWord()) {
            singleCommandHandler(similarWords.getSingleWord());
        } else {
            multipleCommandsHandler(similarWords);
        }
    }
}
