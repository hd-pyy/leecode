package t8_2tree_search;

public class BSTNode {
    int key;
    Object value;
    BSTNode left;
    BSTNode right;

    public BSTNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
