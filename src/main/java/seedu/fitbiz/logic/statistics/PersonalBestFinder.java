package seedu.fitbiz.logic.statistics;

import java.util.HashMap;

import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.PersonalBest;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.UniqueExerciseList;

/**
 * Finds the personal best of every exercise with valid weight or reps in exercise list.
 */
public class PersonalBestFinder {

    /**
     * Generates and sets personal bests when view-c, add-e or delete-e is called.
     *
     * @param clientInView The client currently in view
     */
    public static void generateAndSetPersonalBest(Client clientInView) {
        UniqueExerciseList exerciseList = clientInView.getExerciseList();
        PersonalBest personalBest = clientInView.getPersonalBest();
        HashMap<ExerciseName, Exercise> pbTable = new HashMap<ExerciseName, Exercise>();

        for (Exercise ex : exerciseList) {
            ExerciseName name = ex.getExerciseName();
            int weight = ex.getExerciseWeight().convertToInt();
            int reps = ex.getExerciseReps().convertToInt();

            if (pbTable.containsKey(name)) {
                int pbWeight = pbTable.get(name).getExerciseWeight().convertToInt();
                int pbReps = pbTable.get(name).getExerciseReps().convertToInt();

                if (pbWeight < weight) {
                    pbTable.put(name, ex);

                } else if (pbWeight == weight) {
                    if (pbReps < reps) {
                        pbTable.put(name, ex);
                    }
                }
            } else if (hasWeightOrReps(ex)) { // has one or both
                pbTable.put(name, ex);
            }
        }

        personalBest.setPersonalBest(pbTable.values());
    }

    /**
     * Checks if exercise has either one of weight or reps or both.
     *
     * @param ex The exercise to be checked
     * @return True if the exercise has either weight or reps or both.
     */
    private static boolean hasWeightOrReps(Exercise ex) {
        boolean hasWeight = !ex.getExerciseWeight().toString().equals("");
        boolean hasReps = !ex.getExerciseReps().toString().equals("");
        return hasWeight || hasReps;
    }

}
