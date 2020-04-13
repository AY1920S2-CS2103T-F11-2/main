package seedu.address.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddExerciseCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteExerciseCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditExerciseCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GraphCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.ViewCommand;

public class AutocompleteTest {

    // Unambiguous command: "add-c"
    private static final String ADD_COMMAND = "add-c";
    private static final int ADD_CURRENT_CARET_POSITION = 5;
    private static final int ADD_CARET_POSITION_TO_SET = 8; // index right after the "/" in "n/"
    private static final String ADD_FEEDBACK_TO_SET = AddCommand.MESSAGE_USAGE;
    private static final String ADD_COMMAND_TO_SET = "add-c n/ p/ e/ a/ g/ b/ cw/ tw/ h/ r/ s/ t/";

    // Unambiguous command: "add-e"
    private static final String ADD_EXERCISE_COMMAND = "add-e";
    private static final int ADD_EXERCISE_CURRENT_CARET_POSITION = 5;
    private static final int ADD_EXERCISE_CARET_POSITION_TO_SET = 8; // index right after the "/" in "n/"
    private static final String ADD_EXERCISE_FEEDBACK_TO_SET = AddExerciseCommand.MESSAGE_USAGE;
    private static final String ADD_EXERCISE_COMMAND_TO_SET = "add-e n/ d/ reps/ ew/ sets/";

    // Unambiguous command: "clear"
    private static final String CLEAR_COMMAND = "clear";
    private static final int CLEAR_CURRENT_CARET_POSITION = 5;
    private static final int CLEAR_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String CLEAR_FEEDBACK_TO_SET = ClearCommand.MESSAGE_USAGE;
    private static final String CLEAR_COMMAND_TO_SET = "clear";

    // Unambiguous command: "delete-c"
    private static final String DELETE_COMMAND = "delete-c";
    private static final int DELETE_CURRENT_CARET_POSITION = 8;
    private static final int DELETE_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String DELETE_FEEDBACK_TO_SET = DeleteCommand.MESSAGE_USAGE;
    private static final String DELETE_COMMAND_TO_SET = "delete-c "; // extra preamble white space

    // Unambiguous command: "delete-e"
    private static final String DELETE_EXERCISE_COMMAND = "delete-e";
    private static final int DELETE_EXERCISE_CURRENT_CARET_POSITION = 8;
    private static final int DELETE_EXERCISE_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String DELETE_EXERCISE_FEEDBACK_TO_SET = DeleteExerciseCommand.MESSAGE_USAGE;
    private static final String DELETE_EXERCISE_COMMAND_TO_SET = "delete-e "; // extra preamble white space

    // Unambiguous command: "edit-c"
    private static final String EDIT_COMMAND = "edit-c";
    private static final int EDIT_CURRENT_CARET_POSITION = 6;
    private static final int EDIT_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String EDIT_FEEDBACK_TO_SET = EditCommand.MESSAGE_USAGE;
    private static final String EDIT_COMMAND_TO_SET = "edit-c "; // extra preamble white space

    // Unambiguous command: "edit-e"
    private static final String EDIT_EXERCISE_COMMAND = "edit-e";
    private static final int EDIT_EXERCISE_CURRENT_CARET_POSITION = 6;
    private static final int EDIT_EXERCISE_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String EDIT_EXERCISE_FEEDBACK_TO_SET = EditExerciseCommand.MESSAGE_USAGE;
    private static final String EDIT_EXERCISE_COMMAND_TO_SET = "edit-e "; // extra preamble white space

    // Unambiguous command: "exit"
    private static final String EXIT_COMMAND = "exi";
    private static final int EXIT_CURRENT_CARET_POSITION = 3;
    private static final int EXIT_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String EXIT_FEEDBACK_TO_SET = ExitCommand.MESSAGE_USAGE;
    private static final String EXIT_COMMAND_TO_SET = "exit";

    // Unambiguous command: "export"
    private static final String EXPORT_COMMAND = "exp";
    private static final int EXPORT_CURRENT_CARET_POSITION = 3;
    private static final int EXPORT_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String EXPORT_FEEDBACK_TO_SET = ExportCommand.MESSAGE_USAGE;
    private static final String EXPORT_COMMAND_TO_SET = "export";

    // Unambiguous command: "filter-c"
    private static final String FILTER_COMMAND = "filt";
    private static final int FILTER_CURRENT_CARET_POSITION = 4;
    private static final int FILTER_CARET_POSITION_TO_SET = 11; // index right after the "/" in "t/"
    private static final String FILTER_FEEDBACK_TO_SET = FilterCommand.MESSAGE_USAGE;
    private static final String FILTER_COMMAND_TO_SET = "filter-c t/ s/";

    // Unambiguous command: "find-c"
    private static final String FIND_COMMAND = "fin";
    private static final int FIND_CURRENT_CARET_POSITION = 3;
    private static final int FIND_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String FIND_FEEDBACK_TO_SET = FindCommand.MESSAGE_USAGE;
    private static final String FIND_COMMAND_TO_SET = "find-c "; // extra preamble white space

    // Unambiguous command: "graph"
    private static final String GRAPH_COMMAND = "gra";
    private static final int GRAPH_CURRENT_CARET_POSITION = 3;
    private static final int GRAPH_CARET_POSITION_TO_SET = 8; // index right after the "/" in "n/"
    private static final String GRAPH_FEEDBACK_TO_SET = GraphCommand.MESSAGE_USAGE;
    private static final String GRAPH_COMMAND_TO_SET = "graph n/ a/ sd/ ed/";

    // Unambiguous command: "help"
    private static final String HELP_COMMAND = "hel";
    private static final int HELP_CURRENT_CARET_POSITION = 3;
    private static final int HELP_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String HELP_FEEDBACK_TO_SET = HelpCommand.MESSAGE_USAGE;
    private static final String HELP_COMMAND_TO_SET = "help";

    // Unambiguous command: "list-c"
    private static final String LIST_COMMAND = "lis";
    private static final int LIST_CURRENT_CARET_POSITION = 3;
    private static final int LIST_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String LIST_FEEDBACK_TO_SET = ListCommand.MESSAGE_USAGE;
    private static final String LIST_COMMAND_TO_SET = "list-c";

    // Unambiguous command: "schedule"
    private static final String SCHEDULE_COMMAND = "sch";
    private static final int SCHEDULE_CURRENT_CARET_POSITION = 3;
    private static final int SCHEDULE_CARET_POSITION_TO_SET = 9; // index just after first white space
    private static final String SCHEDULE_FEEDBACK_TO_SET = ScheduleCommand.MESSAGE_USAGE;
    private static final String SCHEDULE_COMMAND_TO_SET = "schedule  sch/"; // extra preamble white space

    // Unambiguous command: "view-c"
    private static final String VIEW_COMMAND = "vie";
    private static final int VIEW_CURRENT_CARET_POSITION = 3;
    private static final int VIEW_CARET_POSITION_TO_SET = Integer.MAX_VALUE; // index just after first white space
    private static final String VIEW_FEEDBACK_TO_SET = ViewCommand.MESSAGE_USAGE;
    private static final String VIEW_COMMAND_TO_SET = "view-c "; // extra preamble white space

    // Ambiguous commands
    private static final String AC_COMMAND = "a";
    private static final int AC_CURRENT_CARET_POSITION = 1;
    private static final int AC_CARET_POSITION_TO_SET = Integer.MAX_VALUE;
    private static final String AC_FEEDBACK_TO_SET = Autocomplete.FEEDBACK_MULTIPLE_COMMANDS + "[add-e, add-c]";
    private static final String AC_COMMAND_TO_SET = "add-";

    // Invalid commands
    private static final String IC_COMMAND = "FitBiz!!";
    private static final int IC_CURRENT_CARET_POSITION = 8;
    private static final Integer IC_CARET_POSITION_TO_SET = null;
    private static final String IC_FEEDBACK_TO_SET = Autocomplete.FEEDBACK_NO_COMMANDS;
    private static final String IC_COMMAND_TO_SET = null;

    // Using tab while command is completed to go to next prefix for "graph"
    private static final int GRAPH_FIRST_PREFIX_INDEX = 8;
    private static final int GRAPH_SECOND_PREFIX_INDEX = 11;
    private static final int GRAPH_THIRD_PREFIX_INDEX = 15;
    private static final int GRAPH_LAST_PREFIX_INDEX = 19;

    // Using tab when command has white spaces and no "/" prefixes
    private static final String WHITE_SPACES_WITHOUT_PREFIX = "export random things";
    private static final int WHITE_SPACES_WITHOUT_PREFIX_INDEX = 20;

    private Autocomplete autoComplete = new Autocomplete();

    // ============ START OF FITBIZ COMMAND TESTS

    @Test
    public void execute_addCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(ADD_COMMAND, ADD_CURRENT_CARET_POSITION);

        assertEquals(ADD_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(ADD_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(ADD_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_addExerciseCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(ADD_EXERCISE_COMMAND, ADD_EXERCISE_CURRENT_CARET_POSITION);

        assertEquals(ADD_EXERCISE_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(ADD_EXERCISE_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(ADD_EXERCISE_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_clearCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(CLEAR_COMMAND, CLEAR_CURRENT_CARET_POSITION);

        assertEquals(CLEAR_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(CLEAR_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(CLEAR_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_deleteCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(DELETE_COMMAND, DELETE_CURRENT_CARET_POSITION);

        assertEquals(DELETE_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(DELETE_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(DELETE_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_deleteExerciseCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(DELETE_EXERCISE_COMMAND,
                DELETE_EXERCISE_CURRENT_CARET_POSITION);

        assertEquals(DELETE_EXERCISE_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(DELETE_EXERCISE_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(DELETE_EXERCISE_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_editCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(EDIT_COMMAND, EDIT_CURRENT_CARET_POSITION);

        assertEquals(EDIT_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(EDIT_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(EDIT_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_editExerciseCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(EDIT_EXERCISE_COMMAND, EDIT_EXERCISE_CURRENT_CARET_POSITION);

        assertEquals(EDIT_EXERCISE_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(EDIT_EXERCISE_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(EDIT_EXERCISE_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_exitCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(EXIT_COMMAND, EXIT_CURRENT_CARET_POSITION);

        assertEquals(EXIT_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(EXIT_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(EXIT_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_exportCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(EXPORT_COMMAND, EXPORT_CURRENT_CARET_POSITION);

        assertEquals(EXPORT_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(EXPORT_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(EXPORT_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_filterCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(FILTER_COMMAND, FILTER_CURRENT_CARET_POSITION);

        assertEquals(FILTER_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(FILTER_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(FILTER_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_findCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(FIND_COMMAND, FIND_CURRENT_CARET_POSITION);

        assertEquals(FIND_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(FIND_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(FIND_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_graphCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(GRAPH_COMMAND, GRAPH_CURRENT_CARET_POSITION);

        assertEquals(GRAPH_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(GRAPH_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(GRAPH_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_helpCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(HELP_COMMAND, HELP_CURRENT_CARET_POSITION);

        assertEquals(HELP_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(HELP_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(HELP_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_listCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(LIST_COMMAND, LIST_CURRENT_CARET_POSITION);

        assertEquals(LIST_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(LIST_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(LIST_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_scheduleCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(SCHEDULE_COMMAND, SCHEDULE_CURRENT_CARET_POSITION);

        assertEquals(SCHEDULE_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(SCHEDULE_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(SCHEDULE_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_viewCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(VIEW_COMMAND, VIEW_CURRENT_CARET_POSITION);

        assertEquals(VIEW_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(VIEW_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(VIEW_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    // ============ END OF FITBIZ COMMAND TESTS

    @Test
    public void execute_ambiguousCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(AC_COMMAND, AC_CURRENT_CARET_POSITION);

        assertEquals(AC_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(AC_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(AC_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_invalidCommand_returnsCorrectAutocompleteResult() {
        AutocompleteResult result = autoComplete.execute(IC_COMMAND, IC_CURRENT_CARET_POSITION);

        assertEquals(IC_COMMAND_TO_SET, result.getTextToSet());
        assertEquals(IC_CARET_POSITION_TO_SET, result.getCaretPositionToSet());
        assertEquals(IC_FEEDBACK_TO_SET, result.getTextToFeedback());
    }

    @Test
    public void execute_graphCommandAlreadyCompleted_returnsCorrectCaretPositions() {
        AutocompleteResult firstResult = autoComplete.execute(GRAPH_COMMAND, GRAPH_CURRENT_CARET_POSITION);
        assertEquals(GRAPH_FIRST_PREFIX_INDEX, firstResult.getCaretPositionToSet());

        AutocompleteResult secondResult = autoComplete.execute(GRAPH_COMMAND_TO_SET, GRAPH_FIRST_PREFIX_INDEX);
        assertEquals(GRAPH_SECOND_PREFIX_INDEX, secondResult.getCaretPositionToSet());

        AutocompleteResult thirdResult = autoComplete.execute(GRAPH_COMMAND_TO_SET, GRAPH_SECOND_PREFIX_INDEX);
        assertEquals(GRAPH_THIRD_PREFIX_INDEX, thirdResult.getCaretPositionToSet());

        AutocompleteResult lastResult = autoComplete.execute(GRAPH_COMMAND_TO_SET, GRAPH_THIRD_PREFIX_INDEX);
        assertEquals(GRAPH_LAST_PREFIX_INDEX, lastResult.getCaretPositionToSet());

        AutocompleteResult wrapAroundResult = autoComplete.execute(GRAPH_COMMAND_TO_SET, GRAPH_LAST_PREFIX_INDEX);
        assertEquals(GRAPH_FIRST_PREFIX_INDEX, wrapAroundResult.getCaretPositionToSet());
    }

    @Test
    public void execute_exportCommandAlreadyCompleted_returnsAllNullResults() {
        AutocompleteResult result = autoComplete.execute(WHITE_SPACES_WITHOUT_PREFIX,
                WHITE_SPACES_WITHOUT_PREFIX_INDEX);

        assertEquals(null, result.getTextToSet());
        assertEquals(null, result.getCaretPositionToSet());
        assertEquals(null, result.getTextToFeedback());
    }

}
