package seedu.fitbiz.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbiz.logic.commands.ExerciseCommandTestUtil.VALID_EXERCISE_SETS_BENCH;
import static seedu.fitbiz.testutil.Assert.assertThrows;
import static seedu.fitbiz.testutil.TypicalExercises.BENCH;
import static seedu.fitbiz.testutil.TypicalExercises.PUSHUP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbiz.model.exercise.exceptions.DuplicateExerciseException;
import seedu.fitbiz.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fitbiz.testutil.ExerciseBuilder;

public class UniqueExerciseListTest {

    private final UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.contains(null));
    }

    @Test
    public void contains_exerciseNotInList_returnsFalse() {
        assertFalse(uniqueExerciseList.contains(PUSHUP));
    }

    @Test
    public void contains_exerciseInList_returnsTrue() {
        uniqueExerciseList.add(PUSHUP);
        assertTrue(uniqueExerciseList.contains(PUSHUP));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueExerciseList.add(PUSHUP);
        Exercise editedPushup = new ExerciseBuilder(PUSHUP).withExerciseSets(VALID_EXERCISE_SETS_BENCH).build();
        assertTrue(uniqueExerciseList.contains(editedPushup));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.add(null));
    }

    @Test
    public void add_duplicateExercise_throwsDuplicateExerciseException() {
        uniqueExerciseList.add(PUSHUP);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.add(PUSHUP));
    }

    @Test
    public void setExercise_nullTargetExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercise(null, PUSHUP));
    }

    @Test
    public void setExercise_nullEditedExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercise(PUSHUP, null));
    }

    @Test
    public void setExercise_targetExerciseNotInList_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.setExercise(PUSHUP, PUSHUP));
    }

    @Test
    public void setExercise_editedExerciseIsSameExercise_success() {
        uniqueExerciseList.add(PUSHUP);
        uniqueExerciseList.setExercise(PUSHUP, PUSHUP);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(PUSHUP);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasSameIdentity_success() {
        uniqueExerciseList.add(PUSHUP);
        Exercise editedPushup = new ExerciseBuilder(PUSHUP).withExerciseSets(VALID_EXERCISE_SETS_BENCH).build();
        uniqueExerciseList.setExercise(PUSHUP, editedPushup);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(editedPushup);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasDifferentIdentity_success() {
        uniqueExerciseList.add(PUSHUP);
        uniqueExerciseList.setExercise(PUSHUP, BENCH);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(BENCH);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasNonUniqueIdentity_throwsDuplicateExerciseException() {
        uniqueExerciseList.add(PUSHUP);
        uniqueExerciseList.add(BENCH);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.setExercise(PUSHUP, BENCH));
    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.remove(PUSHUP));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        uniqueExerciseList.add(PUSHUP);
        uniqueExerciseList.remove(PUSHUP);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_nullUniqueExerciseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((UniqueExerciseList) null));
    }

    @Test
    public void setExercises_uniqueExerciseList_replacesOwnListWithProvidedUniqueExerciseList() {
        uniqueExerciseList.add(PUSHUP);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(BENCH);
        uniqueExerciseList.setExercises(expectedUniqueExerciseList);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((List<Exercise>) null));
    }

    @Test
    public void setExercises_list_replacesOwnListWithProvidedList() {
        uniqueExerciseList.add(PUSHUP);
        List<Exercise> exerciseList = Collections.singletonList(BENCH);
        uniqueExerciseList.setExercises(exerciseList);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(BENCH);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_listWithDuplicateExercises_throwsDuplicateExerciseException() {
        List<Exercise> listWithDuplicateExercises = Arrays.asList(PUSHUP, PUSHUP);
        assertThrows(DuplicateExerciseException.class, () ->
            uniqueExerciseList.setExercises(listWithDuplicateExercises));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueExerciseList.asUnmodifiableObservableList().remove(0));
    }
}
