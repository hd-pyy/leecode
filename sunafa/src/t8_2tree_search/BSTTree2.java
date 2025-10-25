public class BSTTree2<T extends Comparable<T>> {
    // 根节点
    BSTNode2<T> root;

    // 静态内部类（如果BSTNode2是内部类的话）
    static class BSTNode2<T extends Comparable<T>> {
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

    // 查找方法 非递归实现
    public Object get(T key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");

        BSTNode2<T> node = root;
        while (node != null) {
            // compareTo
            /***
             * -1 : key<node.key
             * 1 : key>node.key
             * 0      ==
             */
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    // 递归查找
    public Object getRecursive(T key) {
        return getRecursive(root, key);
    }

    private Object getRecursive(BSTNode2<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getRecursive(node.left, key);
        } else if (cmp > 0) {
            return getRecursive(node.right, key);
        } else {
            return node.value;
        }
    }

    // 插入方法
    public void put(T key, Object value) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");

        if (root == null) {
            root = new BSTNode2<>(key, value);
            return;
        }

        BSTNode2<T> node = root;
        BSTNode2<T> parent = null;

        while (node != null) {
            parent = node;
            int cmp = key.compareTo(node.key);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                // 键已存在，更新值
                node.value = value;
                return;
            }
        }

        // 插入新节点
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = new BSTNode2<>(key, value);
        } else {
            parent.right = new BSTNode2<>(key, value);
        }
    }

    // 递归插入
    public void putRecursive(T key, Object value) {
        root = putRecursive(root, key, value);
    }

    private BSTNode2<T> putRecursive(BSTNode2<T> node, T key, Object value) {
        if (node == null) {
            return new BSTNode2<>(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putRecursive(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putRecursive(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    // 删除方法
    public void delete(T key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        root = delete(root, key);
    }

    private BSTNode2<T> delete(BSTNode2<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            // 找到要删除的节点
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 有两个子节点：找到右子树的最小节点
            BSTNode2<T> minNode = findMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = deleteMin(node.right);
        }
        return node;
    }

    private BSTNode2<T> findMin(BSTNode2<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private BSTNode2<T> deleteMin(BSTNode2<T> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(BSTNode2<T> node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.key + " ");
        inOrder(node.right);
    }

    // 获取最小值
    public T min() {
        if (root == null) return null;
        return findMin(root).key;
    }

    // 获取最大值
    public T max() {
        if (root == null) return null;
        BSTNode2<T> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    // 判断是否包含键
    public boolean contains(T key) {
        return get(key) != null;
    }

    // 获取树的大小
    public int size() {
        return size(root);
    }

    private int size(BSTNode2<T> node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }


    public static void main(String[] args) {
        BSTTree2<Integer> tree = new BSTTree2<>();

        // 插入数据
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(4, "Four");

        // 查找
        System.out.println(tree.get(3)); // "Three"
        System.out.println(tree.contains(6)); // false

        // 遍历
        tree.inOrder(); // 2 3 4 5 7

        // 删除
        tree.delete(3);
        tree.inOrder(); // 2 4 5 7

        // 最值
        System.out.println("Min: " + tree.min()); // 2
        System.out.println("Max: " + tree.max()); // 7
        System.out.println("Size: " + tree.size()); // 4
    }
}