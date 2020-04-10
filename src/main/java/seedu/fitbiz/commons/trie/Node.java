package seedu.fitbiz.commons.trie;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

/**
 * This class represents a single node of the trie data structure.
 */
public class Node {

    private static final String EMPTY_STRING = "";
    private static final int SIZE_OF_ONE = 1;

    private char letter;
    private Node parent;
    private HashMap<Character, Node> children;
    private boolean isWordEnd;

    /**
     * Constructs the root {@code Node}.
     */
    public Node() {
        children = new HashMap<>();
        isWordEnd = false;
        parent = null;
    }

    /**
     * Constructs a child node {@code Node} with a parent node {@code parent}.
     *
     * @param letter the letter that this new child contains
     * @param parent the parent node {@code Node} of this child, which must not be
     *               {@code null}
     */
    public Node(char letter, Node parent) {
        requireNonNull(parent);
        this.letter = letter;
        this.parent = parent;
        children = new HashMap<>();
        isWordEnd = false;
    }

    /**
     * Returns {@code children}.
     */
    public HashMap<Character, Node> getChildren() {
        return children;
    }

    /**
     * Returns true iff {@code children} is of size one.
     */
    public boolean hasSingleChild() {
        return children.size() == SIZE_OF_ONE;
    }

    /**
     * Returns the only child of this node. {@code children} must only contain one
     * child child to start with.
     */
    public Node getSingleChild() {
        assert hasSingleChild();
        for (Node node : children.values()) {
            return node;
        }
        return null;
    }

    /**
     * Returns true iff {@code children} contains {@code letter}.
     */
    public boolean hasChild(char letter) {
        return children.containsKey(letter);
    }

    /**
     * Returns the child {@Node} in {@code children}.
     */
    public Node getChild(char letter) {
        return children.get(letter);
    }

    /**
     * Adds a child {@code Node} to this {@code Node}.
     */
    public void addChild(Node child) {
        children.put(child.getLetter(), child);
    }

    public void setIsWordEnd(boolean bool) {
        isWordEnd = bool;
    }

    public boolean isWordEnd() {
        return isWordEnd;
    }

    public char getLetter() {
        return letter;
    }

    /**
     * Returns true iff @{code parent} is null, meaning that this node must be the
     * root node.
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Constructs the word by recursively going up the parent node.
     *
     * @return the constructed word string
     */
    public String constructWord() {
        if (isRoot()) {
            return EMPTY_STRING;
        }
        return parent.constructWord() + getLetter();
    }
}
