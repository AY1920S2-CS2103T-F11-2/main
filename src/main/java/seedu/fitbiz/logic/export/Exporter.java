package seedu.fitbiz.logic.export;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import seedu.fitbiz.logic.commands.exceptions.CommandException;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.storage.StorageReaderWriter;

/**
 * This class contains the logic behind the exporting of files.
 */
public class Exporter {

    public static final String DEFAULT_EXERCISES_DIRECTORY = "exports";
    public static final String CSV_FILE_EXTENSION = ".csv";
    public static final String EMPTY_EXERCISES_MESSAGE =
            "This client currently does not have any exercises to export.";
    public static final String EXERCISE_CSV_HEADERS = "Date,Exercise Name,Reps,Weights,Sets";

    /**
     * Exports the provided {@code client}'s exercises as a CSV file and saves to storage
     * via @{code StorageReaderWriter}.
     *
     * @param client the {@code Client} to export
     * @throws CommandException if {@code client} does not have any exercises stored
     */
    public static String exportExercisesAsCsv(Client client) throws CommandException {
        List<Exercise> exercises = client.getExerciseList().asUnmodifiableObservableList();

        if (exercises.isEmpty()) {
            throw new CommandException(EMPTY_EXERCISES_MESSAGE);
        }

        String fileName = client.getName().fullName + CSV_FILE_EXTENSION;
        Path filePath = Paths.get(DEFAULT_EXERCISES_DIRECTORY, fileName);
        StorageReaderWriter storageReaderWriter = new StorageReaderWriter(filePath);

        List<String> lines = new LinkedList<>();

        lines.add(EXERCISE_CSV_HEADERS);

        for (Exercise e : exercises) {
            String dateString = e.getExerciseDate().displayValue;
            String nameString = e.getExerciseName().value;
            String repsString = e.getExerciseReps().value;
            String weightString = e.getExerciseWeight().value;
            String setsString = e.getExerciseSets().value;
            String line = String.format("%s,%s,%s,%s,%s",
                    dateString, nameString, repsString, weightString, setsString);
            lines.add(line);
        }

        storageReaderWriter.writeToStorage(lines);

        return fileName;
    }
}
