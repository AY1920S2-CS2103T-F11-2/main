package seedu.fitbiz.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fitbiz.commons.core.index.Index;
import seedu.fitbiz.model.exercise.exceptions.DuplicateExerciseException;
import seedu.fitbiz.model.exercise.exceptions.ExerciseNotFoundException;

/**
 * A list of exercises that enforces uniqueness between its elements and does
 * not allow nulls. A exercise is considered unique by comparing using
 * {@code Exercise#isSameexercise(Exercise)}. As such, adding and updating of
 * exercises uses Exercise#isSameexercise(Exercise) for equality so as to ensure
 * that the exercise being added or updated is unique in terms of identity in
 * the UniqueexerciseList. However, the removal of a exercise uses
 * Exercise#equals(Object) so as to ensure that the exercise with exactly the
 * same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Exercise#isSameexercise(Exercise)
 */
public class UniqueExerciseList implements Iterable<Exercise> {

    private final ObservableList<Exercise> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exercise> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent exercise as the given
     * argument.
     */
    public boolean contains(Exercise toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExercise);
    }

    /**
     * Returns {@code Exercise} specified by the {@code Index}.
     */
    public Exercise getExercise(Index index) {
        requireNonNull(index);
        return internalList.get(index.getZeroBased());
    }

    /**
     * Adds an exercise to the list without ensuring any order. The exercise must
     * not already exist in the list.
     */
    public void add(Exercise toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExerciseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Inserts an exercise to the list while ensuring list is sorted by the exercise
     * date in descending order and by exercise name in ascending order.
     *
     * <p>
     * The exercise must not already exist in the list. This basically does
     * insertion sort based on the fact that the list must already be sorted in the
     * first place. Do not use this method to add many exercises at once as it is
     * inefficient.
     */
    public void addToSorted(Exercise toAdd) {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new DuplicateExerciseException();
        }

        int idx = 0;
        LocalDate toAddDate = toAdd.getExerciseDate().value;
        String toAddName = toAdd.getExerciseName().value.toLowerCase();
        for (Exercise curr : internalList) {
            LocalDate currDate = curr.getExerciseDate().value;
            String currName = curr.getExerciseName().value.toLowerCase();
            if (toAddDate.compareTo(currDate) > 0) {
                // already at correct position
                break;
            } else if (toAddDate.compareTo(currDate) == 0) {
                // sort by name
                if (toAddName.compareTo(currName) <= 0) {
                    break;
                } else {
                    idx++;
                }
            } else {
                // toAddDate is later than currDate
                idx++;
            }
        }
        internalList.add(idx, toAdd);
    }

    /**
     * Replaces the exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the list. The exercise identity of
     * {@code editedExercise} must not be the same as another existing exercise in
     * the list.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExerciseNotFoundException();
        }

        remove(target);

        if (!target.isSameExercise(editedExercise) && contains(editedExercise)) {
            throw new DuplicateExerciseException();
        }

        addToSorted(editedExercise);
    }

    /**
     * Sorts the list by the exercise date in descending order, and if the dates
     * are equal, then by the exercise name in ascending order.
     */
    public void sortByExerciseDateAndName() {
        Comparator<Exercise> byExerciseDate = (Exercise e1, Exercise e2) -> {
            LocalDate e1Date = e1.getExerciseDate().value;
            LocalDate e2Date = e2.getExerciseDate().value;
            String e1Name = e1.getExerciseName().value.toUpperCase();
            String e2Name = e2.getExerciseName().value.toUpperCase();
            if (e2Date.compareTo(e1Date) == 0) {
                return e1Name.compareTo(e2Name);
            }
            return e2Date.compareTo(e1Date);
        };
        FXCollections.sort(internalList, byExerciseDate);
    }

    /**
     * Removes the equivalent exercise from the list. The exercise must exist in the
     * list.
     */
    public void remove(Exercise toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ExerciseNotFoundException();
        }
    }

    public void setExercises(UniqueExerciseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code exercises}. {@code exercises}
     * must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        requireAllNonNull(exercises);
        if (!exercisesAreUnique(exercises)) {
            throw new DuplicateExerciseException();
        }

        internalList.setAll(exercises);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exercise> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Exercise> iterator() {
        return internalList.iterator();
    }

    /**
     * Return size of the exercise list.
     */
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueExerciseList // instanceof handles nulls
                        && internalList.equals(((UniqueExerciseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code exercises} contains only unique exercises.
     */
    private boolean exercisesAreUnique(List<Exercise> exercises) {
        for (int i = 0; i < exercises.size() - 1; i++) {
            for (int j = i + 1; j < exercises.size(); j++) {
                if (exercises.get(i).isSameExercise(exercises.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (Exercise ex : internalList) {
            toReturn += ex.toString() + "\n";
        }
        return toReturn.trim();
    }
}
