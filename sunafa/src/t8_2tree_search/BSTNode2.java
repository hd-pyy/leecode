package t8_2tree_search;

// 泛型上限 实现该接口后 泛型必须是Comparable接口的子类型才行
public class BSTNode2<T extends Comparable<T>> {
    T key;
    Object value;
    BSTNode2<T> left;
    BSTNode2<T> right;

    public BSTNode2(T key, Object value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode2(T key, Object value, BSTNode2<T> left, BSTNode2<T> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
