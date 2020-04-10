package seedu.fitbiz.commons.trie;

import static seedu.fitbiz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

/**
 * This is a wrapper class to hold the information returned by
 * {@code Trie#listAllSimilarWords}. The member {@code longestPrefixString} will
 * contain the longest common prefix of the words in {@code similarWords}.
 */
public class SimilarWordsResult {
    public final String longestPrefixString;
    public final ArrayList<String> similarWords;

    public SimilarWordsResult(String longestPrefixString, ArrayList<String> similarWords) {
        requireAllNonNull(longestPrefixString, similarWords);
        this.longestPrefixString = longestPrefixString;
        this.similarWords = similarWords;
    }

    public boolean hasNoResult() {
        return similarWords.isEmpty();
    }

    public boolean hasOnlyOneWord() {
        return similarWords.size() == 1;
    }

    public String getSingleWord() {
        assert hasOnlyOneWord();
        return similarWords.get(0);
    }

    public String getLongestPrefix() {
        return longestPrefixString;
    }

    /**
     * Returns a copy of the list {@code similarWords}, any modifications to this
     * returned list will not affect the original list {@code similarWords}.
     */
    public ArrayList<String> getSimilarWords() {
        return new ArrayList<String>(similarWords);
    }

}
