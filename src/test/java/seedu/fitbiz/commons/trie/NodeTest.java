package seedu.fitbiz.commons.trie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NodeTest {

    private static final char VALID_CHAR_1 = 'a';
    private static final char VALID_CHAR_2 = 'b';

    @Test
    public void constructor_nullParent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Node(VALID_CHAR_1, null));
    }

    @Test
    public void isRoot_rootNode_returnsTrue() {
        Node root = new Node();

        assertEquals(true, root.isRoot());
    }

    @Test
    public void isRoot_childNode_returnsFalse() {
        Node root = new Node();

        Node child = new Node(VALID_CHAR_1, root);

        assertEquals(false, child.isRoot());
    }

    @Test
    public void hasSingleChild_oneChild_returnsTrue() {
        Node root = new Node();

        Node child = new Node(VALID_CHAR_1, root);

        root.addChild(child);

        assertEquals(true, root.hasSingleChild());
    }

    @Test
    public void hasSingleChild_multipleChilds_returnsFalse() {
        Node root = new Node();

        Node child1 = new Node(VALID_CHAR_1, root);
        Node child2 = new Node(VALID_CHAR_2, child1);

        root.addChild(child1);
        root.addChild(child2);

        assertEquals(false, root.hasSingleChild());
    }

    @Test
    public void getSingleChild_oneChild_returnsCorrectChild() {
        Node root = new Node();

        Node child1 = new Node(VALID_CHAR_1, root);

        root.addChild(child1);

        assertEquals(child1, root.getSingleChild());
    }

    @Test
    public void getSingleChild_multipleChilds_throwsAssertionError() {
        Node root = new Node();

        Node child1 = new Node(VALID_CHAR_1, root);
        Node child2 = new Node(VALID_CHAR_2, child1);

        root.addChild(child1);
        root.addChild(child2);

        assertThrows(AssertionError.class, () -> root.getSingleChild());
    }

}
