package t11_BTree;

import java.util.Arrays;

public class BTree {

    static class Node {
        // children = key + 1
        int[] keys; //
        Node[] children;//
        int keyNum;// 有效关键字数目
        boolean leaf = true;
        int t;// 最小度数

        public Node(int t) { // t>=2
            this.t = t;
            // 最多孩子数等于最小度数乘二 这是b树的约定
            this.children = new Node[2 * t];
            this.keys = new int[2 * t - 1];
        }

        // 用来打印有效key
        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNum));
        }

        // 多路查找
        public Node get(int key) {
            int i = 0;
            while (i < keyNum) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    // 当前节点没找到 退出当前循环 进入下一个节点查找
                    break;
                }
                // 小于 继续循环
                i++;
            }

            // 执行到此时 keys[i]>key  或者 i == keyNum
            if (leaf) { // 当前节点为叶子结点 而且退出了上面的循环 说明遍历完都没找到 此时 直接返回 null
                return null;
            }

            // 非叶子情况 递归调用第 i 个孩子
            return children[i].get(key);
        }

        // 向keys指定索引index处插入 key
        public void insertKey(int key, int index) {
//            // 从后往前 移动元素 在索引index处留出一个空位
//            for (int i = keyNum - 1; i >= index; i--) {
//                keys[i + 1] = keys[i];
//            }
            // 或者直接拷贝后半个数组数组
            System.arraycopy(keys, index, keys, index + 1, keyNum - index);
            keys[index] = key;
            keyNum++;
        }

        // 向 children 指定索引 index 插入 child
        public void insertChild(Node child, int index) {
            // 从后往前 移动元素
//            for (int i = keyNum; i >= index; i--) {
//                children[i + 1] = children[i];
//            }
            System.arraycopy(children, index, children, index + 1, keyNum - index);
            children[index] = child;
        }

        // 删除指定索引的key
        public int removeKey(int index) {
            int removed = keys[index];
            // 从前往后移动元素
            for (int i = index; i < keyNum - 1; i++) {
                keys[i] = keys[i + 1];
            }
            keyNum--;
            return removed;
        }

        // 删除指定索引的child
        public Node removeChild(int index) {
            Node removed = children[index];
            // 从前往后移动元素
            for (int i = index; i < keyNum; i++) {
                children[i] = children[i + 1];
            }
            children[keyNum] = null; // 清理最后一个引用
            return removed;
        }

        // 判断节点是否已满
        public boolean isFull() {
            return keyNum == 2 * t - 1;
        }

        // 判断节点关键字是否过少
        public boolean isFew() {
            return keyNum < t - 1;
        }
    }

    private Node root;
    private int t; // 书中节点最小度数
    // 两个数为常亮 一旦树被创建 就不能变了
    final int MIN_KEY_NUM; // 最小key数目
    final int MAX_KEY_NUM; // 最大key 数目

    public BTree() {
        // 无参构造调用有参构造 给 最小度数赋初值为2
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        this.root = new Node(t);
        MAX_KEY_NUM = 2 * t - 1;
        MIN_KEY_NUM = t - 1;
    }

    // 查找
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 插入
    public void put(int key) {
        doPut(root, key);
        // 如果根节点已满，需要分裂
        if (root.isFull()) {
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(root, 0);
//            split(newRoot, 0);
            root = newRoot;
        }
    }

    private void doPut(Node node, int key) {
        int i = 0;
        // 找到插入位置
        while (i < node.keyNum) {
            if (node.keys[i] == key) {
                return; // 键已存在 走更新逻辑
            }
            if (node.keys[i] > key) {
                break; // 退出循环 找到了插入位置 记为 i
            }
            i++;
        }

        if (node.leaf) {
            // 叶子节点直接插入
            node.insertKey(key, i);
        } else {
            // 非叶子节点，递归插入到子节点
//            Node child = node.children[i];
//            if (child.isFull()) {
//                // 子节点已满，需要分裂
//                split(node, i);
//                // 分裂后需要重新确定插入位置
//                if (key > node.keys[i]) {
//                    i++;
//                }
//            }
            doPut(node.children[i], key);
        }
    }

    // 分裂节点

    /**
     *
     * @param left 被分裂节点
     * @param parent 被分裂节点的父节点
     * @param index
     */
    private void split(Node left, Node parent, int index) {
        Node right = new Node(t);
        // right 是否是叶子节点 跟 left 相同 他俩在同一层
        right.leaf = left.leaf;

        // 开始分裂
        // 1、left 中一部分 key 移动到 right
        /**
         *        // 将左节点的后半部分关键字移动到右节点
         *         // 源数组: left.keys - 左节点的关键字数组
         *         // 源起始位置: t - 从左节点的第t个关键字开始拷贝（索引从0开始）
         *         // 目标数组: right.keys - 右节点的关键字数组
         *         // 目标起始位置: 0 - 拷贝到右节点的开头位置
         *         // 拷贝长度: t-1 - 拷贝t-1个关键字（左节点总共2t-1个关键字，保留前t-1个，移动后t-1个）
         */
        System.arraycopy(left.keys, t , right.keys,0,t-1);
        right.keyNum = t-1;// 有效关键字数目 拷贝了 t-1 所以有效也是这么多
        left.keyNum = t-1;// 有效关键字数目 拷贝了 t-1 所以有效也是这么多

        // 2、拿到中间的 key 拷贝到父节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid,index);

        // 3、right 节点作为parent的孩子 插入到 index+1 处
        parent.insertChild(right,index+1);

    }

    // 删除
    public void remove(int key) {
        doRemove(root, key);
        // 如果根节点没有key且不是叶子，需要降低树高
        if (root.keyNum == 0 && !root.leaf) {
            root = root.children[0];
        }
    }

    private void doRemove(Node node, int key) {
        int i = 0;
        while (i < node.keyNum) {
            if (node.keys[i] == key) {
                // 找到要删除的key
                if (node.leaf) {
                    // 情况1：叶子节点直接删除
                    node.removeKey(i);
                } else {
                    // 情况2：非叶子节点
                    Node leftChild = node.children[i];
                    Node rightChild = node.children[i + 1];

                    if (leftChild.keyNum >= t) {
                        // 情况2a：左孩子有足够的关键字
                        int predecessor = getPredecessor(leftChild);
                        node.keys[i] = predecessor;
                        doRemove(leftChild, predecessor);
                    } else if (rightChild.keyNum >= t) {
                        // 情况2b：右孩子有足够的关键字
                        int successor = getSuccessor(rightChild);
                        node.keys[i] = successor;
                        doRemove(rightChild, successor);
                    } else {
                        // 情况2c：合并左右孩子
                        merge(node, i);
                        doRemove(leftChild, key);
                    }
                }
                return;
            } else if (node.keys[i] > key) {
                break;
            }
            i++;
        }

        // 在当前节点没找到，需要到子节点继续查找
        if (node.leaf) {
            return; // 没找到
        }

        Node child = node.children[i];
        if (child.keyNum < t) {
            // 子节点关键字不足，需要补充
            fill(node, i);
            // 补充后可能需要重新确定子节点
            if (i > node.keyNum) {
                i--;
            }
        }
        doRemove(node.children[i], key);
    }

    // 获取前驱节点（最大的key）
    private int getPredecessor(Node node) {
        while (!node.leaf) {
            node = node.children[node.keyNum];
        }
        return node.keys[node.keyNum - 1];
    }

    // 获取后继节点（最小的key）
    private int getSuccessor(Node node) {
        while (!node.leaf) {
            node = node.children[0];
        }
        return node.keys[0];
    }

    // 合并节点
    private void merge(Node parent, int index) {
        Node left = parent.children[index];
        Node right = parent.children[index + 1];

        // 将父节点的key下移到左节点
        left.insertKey(parent.removeKey(index), left.keyNum);

        // 将右节点的key移动到左节点
        for (int i = 0; i < right.keyNum; i++) {
            left.insertKey(right.keys[i], left.keyNum);
        }

        // 如果不是叶子节点，还需要移动孩子
        if (!left.leaf) {
            for (int i = 0; i <= right.keyNum; i++) {
                left.insertChild(right.children[i], left.keyNum + i);
            }
        }

        // 删除右节点
        parent.removeChild(index + 1);
    }

    // 填充关键字不足的子节点
    private void fill(Node parent, int index) {
        if (index != 0 && parent.children[index - 1].keyNum >= t) {
            // 情况3a：从左兄弟借
            borrowFromLeft(parent, index);
        } else if (index != parent.keyNum && parent.children[index + 1].keyNum >= t) {
            // 情况3b：从右兄弟借
            borrowFromRight(parent, index);
        } else {
            // 情况3c：合并
            if (index != parent.keyNum) {
                merge(parent, index);
            } else {
                merge(parent, index - 1);
            }
        }
    }

    // 从左兄弟借关键字
    private void borrowFromLeft(Node parent, int index) {
        Node child = parent.children[index];
        Node leftSibling = parent.children[index - 1];

        // 右移child的key和children
        child.insertKey(parent.keys[index - 1], 0);
        if (!child.leaf) {
            child.insertChild(leftSibling.removeChild(leftSibling.keyNum), 0);
        }

        // 更新父节点的key
        parent.keys[index - 1] = leftSibling.removeKey(leftSibling.keyNum - 1);
    }

    // 从右兄弟借关键字
    private void borrowFromRight(Node parent, int index) {
        Node child = parent.children[index];
        Node rightSibling = parent.children[index + 1];

        // 添加父节点的key到child
        child.insertKey(parent.keys[index], child.keyNum);
        if (!child.leaf) {
            child.insertChild(rightSibling.removeChild(0), child.keyNum);
        }

        // 更新父节点的key
        parent.keys[index] = rightSibling.removeKey(0);
    }

    // 打印B树
    public void print() {
        print(root, 0);
    }

    private void print(Node node, int level) {
        System.out.println("Level " + level + ": " + node);
        if (!node.leaf) {
            for (int i = 0; i <= node.keyNum; i++) {
                print(node.children[i], level + 1);
            }
        }
    }
}