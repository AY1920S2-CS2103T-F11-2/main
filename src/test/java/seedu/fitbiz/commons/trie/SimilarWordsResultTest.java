package seedu.fitbiz.commons.trie;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SimilarWordsResultTest {

    public static final String EMPTY_WORD = "";
    public static final ArrayList<String> EMPTY_WORD_LIST = new ArrayList<>();
    public static final SimilarWordsResult EMPTY_WORD_RESULT = new SimilarWordsResult(EMPTY_WORD, EMPTY_WORD_LIST);

    public static final String SINGLE_WORD = "help";
    public static final ArrayList<String> SINGLE_WORD_LIST = new ArrayList<>(List.of(SINGLE_WORD));
    public static final SimilarWordsResult SINGLE_WORD_RESULT = new SimilarWordsResult(SINGLE_WORD, SINGLE_WORD_LIST);

    public static final String WORD_1 = "exit";
    public static final String WORD_2 = "edit-e";
    public static final String WORD_3 = "edit-c";
    public static final String LONGEST_COMMON_PREFIX = "e";
    public static final ArrayList<String> MULTIPLE_WORD_LIST = new ArrayList<>(List.of(WORD_1, WORD_2, WORD_3));
    public static final SimilarWordsResult MULTIPLE_WORD_RESULT = new SimilarWordsResult(LONGEST_COMMON_PREFIX,
            MULTIPLE_WORD_LIST);

    @Test
    public void constructor() {
        String nullString = null;
        ArrayList<String> nullList = null;
        String validString = "";
        ArrayList<String> validList = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> new SimilarWordsResult(nullString, validList));
        assertThrows(NullPointerException.class, () -> new SimilarWordsResult(validString, nullList));
        assertDoesNotThrow(() -> new SimilarWordsResult(validString, validList));
    }

    @Test
    public void hasNoResult_onEmptyResult_returnsTrue() {
        assertTrue(EMPTY_WORD_RESULT.hasNoResult());
    }

    @Test
    public void hasNoResult_onNonEmptyResult_returnsFalse() {
        assertFalse(SINGLE_WORD_RESULT.hasNoResult());
        assertFalse(MULTIPLE_WORD_RESULT.hasNoResult());
    }

    @Test
    public void hasOnlyOneWord_onSingleWordResult_returnsTrue() {
        assertTrue(SINGLE_WORD_RESULT.hasOnlyOneWord());
    }

    @Test
    public void hasOnlyOneWord_onNonSingleWordResult_returnsFalse() {
        assertFalse(EMPTY_WORD_RESULT.hasOnlyOneWord());
        assertFalse(MULTIPLE_WORD_RESULT.hasOnlyOneWord());
    }

    @Test
    public void getSingleWord_onSingleWordResult_returnsCorrectString() {
        assertEquals(SINGLE_WORD_RESULT.getSingleWord(), SINGLE_WORD);
    }

    @Test
    public void getSingleWord_onNonSingleWordResult_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> EMPTY_WORD_RESULT.getSingleWord());
        assertThrows(AssertionError.class, () -> MULTIPLE_WORD_RESULT.getSingleWord());
    }

}
