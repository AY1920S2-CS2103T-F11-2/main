package seedu.fitbiz.logic.export;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.testutil.ClientBuilder;
import seedu.fitbiz.testutil.ExerciseBuilder;

public class ExporterTest {

    private Client clientWithoutExercises = new ClientBuilder().build();
    private Client clientWithExercises = new ClientBuilder()
            .withExercisesInExerciseList(new ExerciseBuilder().build()).build();

    @Test
    public void exportExercisesAsCsv_clientWithoutExercises_throwsCommandException() {
        assertThrows(CommandException.class, () -> Exporter.exportExercisesAsCsv(clientWithoutExercises));
    }

    @Test
    public void exportExercisesAsCsv_clientWithExercises_success() {
        assertDoesNotThrow(() -> Exporter.exportExercisesAsCsv(clientWithExercises));
    }

}
