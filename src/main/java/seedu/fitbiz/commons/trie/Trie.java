package seedu.fitbiz.commons.trie;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class represents the trie data structure.
 */
public class Trie {

    private static final String EMPTY_STRING = "";

    private Node root;

    public Trie() {
        root = new Node();
    }

    /**
     * Inserts the given string {@code word} into this trie.
     *
     * @param word
     */
    public void insert(String word) {
        requireNonNull(word);
        Node current = root;

        for (char letter : word.toCharArray()) {
            if (current.hasChild(letter)) {
                current = current.getChild(letter);
            } else {
                Node newChild = new Node(letter, current);
                current.addChild(newChild);
                current = newChild;
            }
        }

        current.setIsWordEnd(true);
    }

    /**
     * Returns the {@code Node} whose constructed word (when constructed using
     * {@code Node#constructWord()}) contain the longest common prefix of the
     * argument {@code word} and all the words currently in this trie. If no such
     * {@code Node} can be found, {@code null} is returned.
     *
     * @param word String to search for
     * @return a {@code Node} or {@null} if no such node can be found
     */
    private Node getLongestPrefixNode(String word) {
        requireNonNull(word);
        Node current = root;

        // this loop should end prematurely if word is not a proper substring
        for (char letter : word.toCharArray()) {
            if (!current.hasChild(letter)) {
                return null;
            }
            current = current.getChild(letter);
        }

        while (current.hasSingleChild()) {
            current = current.getSingleChild();
        }

        return current;
    }

    /**
     * Returns a {@code SimilarWordsResult} object containing the list of similar
     * words in this trie that starts with the parameter {@code word}, and the
     * longest common prefix of these words.
     *
     * @param word string to match
     * @return an object of type {@code SimilarWordsResult}
     */
    public SimilarWordsResult listAllSimilarWords(String word) {
        requireNonNull(word);
        Node longestPrefixNode = getLongestPrefixNode(word);

        ArrayList<String> similarWords = new ArrayList<>();
        Node subtrie = longestPrefixNode;

        // case 1: longest prefix does not exist
        if (subtrie == null) {
            return new SimilarWordsResult(EMPTY_STRING, similarWords);
        }

        // case 2: longest prefix is actually the completed word
        String longestPrefixString = longestPrefixNode.constructWord();
        if (subtrie.isWordEnd()) {
            similarWords.add(longestPrefixString);
            return new SimilarWordsResult(longestPrefixString, similarWords);
        }

        // case 3: longest prefix is not a completed word
        // dfs to find all similar words that contain subtrie
        Stack<Node> stack = new Stack<>();
        stack.push(subtrie);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.isWordEnd()) {
                similarWords.add(current.constructWord());
            } else {
                stack.addAll(current.getChildren().values());
            }
        }

        return new SimilarWordsResult(longestPrefixString, similarWords);
    }
}
