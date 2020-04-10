package seedu.fitbiz.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.fitbiz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fitbiz.logic.commands.ExportCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.model.Model;
import seedu.fitbiz.model.ModelManager;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.testutil.ClientBuilder;
import seedu.fitbiz.testutil.ExerciseBuilder;

public class ExportCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    private Client clientWithoutExercises = new ClientBuilder().build();
    private Client clientWithExercises = new ClientBuilder()
            .withExercisesInExerciseList(new ExerciseBuilder().build()).build();
    private String clientWithExercisesCsvFileName = clientWithExercises.getName().fullName + ".csv";

    @Test
    public void execute_noClientInView_throwsCommandException() {
        assertThrows(CommandException.class, () -> new ExportCommand().execute(model));
    }

    @Test
    public void execute_clientHasNoExercises_throwsCommandException() {
        model.setClientInView(clientWithoutExercises);
        assertThrows(CommandException.class, () -> new ExportCommand().execute(model));
    }

    @Test
    public void execute_clientWithExercises_success() {
        model.setClientInView(clientWithExercises);
        expectedModel.setClientInView(clientWithExercises);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, clientWithExercisesCsvFileName));

        assertCommandSuccess(new ExportCommand(), model, expectedCommandResult, expectedModel);
    }
}
