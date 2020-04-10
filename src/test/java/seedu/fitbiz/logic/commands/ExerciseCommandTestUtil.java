package seedu.fitbiz.logic.commands;

import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_EXERCISE_WEIGHT;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_REPS;
import static seedu.fitbiz.logic.parser.CliSyntax.PREFIX_SETS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.fitbiz.testutil.EditExerciseDescriptorBuilder;

/**
 * Contains helper methods for testing exerise commands.
 */
public class ExerciseCommandTestUtil {

    public static final String VALID_EXERCISE_NAME_PUSHUP = "pushup";
    public static final String VALID_EXERCISE_NAME_BENCH = "Bench Press";
    public static final String VALID_EXERCISE_REPS_PUSHUP = "30";
    public static final String VALID_EXERCISE_REPS_BENCH = "12";
    public static final String VALID_EXERCISE_SETS_PUSHUP = "5";
    public static final String VALID_EXERCISE_SETS_BENCH = "4";
    public static final String VALID_EXERCISE_WEIGHT_PUSHUP = "";
    public static final String VALID_EXERCISE_WEIGHT_BENCH = "100";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String VALID_EXERCISE_DATE_PUSHUP =
        LocalDate.now().minusMonths(4).format(DATE_TIME_FORMATTER);
    public static final String VALID_EXERCISE_DATE_BENCH =
        LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER);

    public static final String EXERCISE_NAME_DESC_PUSHUP =
        " " + PREFIX_NAME + VALID_EXERCISE_NAME_PUSHUP;
    public static final String EXERCISE_NAME_DESC_BENCH =
        " " + PREFIX_NAME + VALID_EXERCISE_NAME_BENCH;
    public static final String EXERCISE_REPS_DESC_PUSHUP =
        " " + PREFIX_REPS + VALID_EXERCISE_REPS_PUSHUP;
    public static final String EXERCISE_REPS_DESC_BENCH =
        " " + PREFIX_REPS + VALID_EXERCISE_REPS_BENCH;
    public static final String EXERCISE_SETS_DESC_PUSHUP =
        " " + PREFIX_SETS + VALID_EXERCISE_SETS_PUSHUP;
    public static final String EXERCISE_SETS_DESC_BENCH =
        " " + PREFIX_SETS + VALID_EXERCISE_SETS_BENCH;
    public static final String EXERCISE_WEIGHT_DESC_PUSHUP =
        " " + PREFIX_EXERCISE_WEIGHT + VALID_EXERCISE_WEIGHT_PUSHUP;
    public static final String EXERCISE_WEIGHT_DESC_BENCH =
        " " + PREFIX_EXERCISE_WEIGHT + VALID_EXERCISE_WEIGHT_BENCH;
    public static final String EXERCISE_DATE_DESC_PUSHUP =
        " " + PREFIX_DATE + VALID_EXERCISE_DATE_PUSHUP;
    public static final String EXERCISE_DATE_DESC_BENCH =
        " " + PREFIX_DATE + VALID_EXERCISE_DATE_BENCH;

    public static final String INVALID_EXERCISE_NAME_DESC =
        " " + PREFIX_NAME + "Pushup&"; // '&' not allowed in names
    public static final String INVALID_EXERCISE_REPS_DESC =
        " " + PREFIX_REPS + "91a"; // 'a' not allowed in reps
    public static final String INVALID_EXERCISE_SETS_DESC =
        " " + PREFIX_SETS + "21a9"; // 'a' not allowed in sets
    public static final String INVALID_EXERCISE_WEIGHT_DESC =
        " " + PREFIX_EXERCISE_WEIGHT + "1o21"; // 'o' not allowed in weight
    public static final String INVALID_EXERCISE_DATE_DESC = " " + PREFIX_DATE + ""; // empty string not allowed for date

    public static final EditExerciseCommand.EditExerciseDescriptor DESC_PUSHUP;
    public static final EditExerciseCommand.EditExerciseDescriptor DESC_BENCH;

    static {
        DESC_PUSHUP = new EditExerciseDescriptorBuilder()
        .withExerciseName(VALID_EXERCISE_NAME_PUSHUP)
            .withExerciseReps(VALID_EXERCISE_REPS_PUSHUP)
            .withExerciseSets(VALID_EXERCISE_SETS_PUSHUP)
            .withExerciseWeight(VALID_EXERCISE_WEIGHT_PUSHUP)
            .withExerciseDate(VALID_EXERCISE_DATE_PUSHUP).build();
        DESC_BENCH = new EditExerciseDescriptorBuilder()
            .withExerciseName(VALID_EXERCISE_NAME_BENCH)
            .withExerciseReps(VALID_EXERCISE_REPS_BENCH)
            .withExerciseSets(VALID_EXERCISE_SETS_BENCH)
            .withExerciseWeight(VALID_EXERCISE_WEIGHT_BENCH)
            .withExerciseDate(VALID_EXERCISE_DATE_BENCH).build();
    }

}
