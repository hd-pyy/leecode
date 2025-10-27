package t8_2tree_search;

import java.util.Arrays;

public class BSTTree1 {
    // 跟节点
    BSTNode root;

    // 根据key返回关联的value值
    /*
    从根节点开始
    如果待查找的值大于根节点的值 向右找 小于向左
    下一个节点查找逻辑相同 所以用递归求解
     */
    public Object getByRec(int key) {
        return rec(root, key);
    }

    // 非递归实现
    public Object get(int key) {
        BSTNode node = root;

        while (node != null) {
            // 1、大于左边
            if (key > node.key) {
                node = node.right;
            } else if (key < node.key) {
                node = node.left;
            } else return node.value;
        }
        return null;
    }

    // 根据key返回关联的value值
    // 向左走到头就是 min
    public Object min() {
        return min(root);
    }

    // 根据key返回关联的value值
    // 向左走到头就是 min
    public Object min(BSTNode node) {
        if (root == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }

        return node.value;
    }

    // 递归实现
    public Object minRec() {
        return doMin(root);
    }


    // 根据key返回关联的value值
    public Object max() {
        return max(root);
    }


    // 在任意节点 找其子树最大值
    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;

    }

    public void put(int key, Object value) {
        // 1、 key 已存在 更新


        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            // 更新之前 现存一下爹
            parent = node;
            // 1、大于左边
            if (key > node.key) {
                node = node.right;
            } else if (key < node.key) {
                node = node.left;
            } else {
                // 1、 key 已存在 更新
                node.value = value;
                return;
            }
        }
        // 循环结束 parent 就是父节点
        BSTNode addNode = new BSTNode(key, value);
        // 但是 现在还是要比较一下key值
        if (parent == null) {
            root = addNode;
            return;
        }
        // 注意此处比较不需要递归进行 思考一下 如果在书中没有找到节点值 那只有可能跟最后一个节点作比较 这是二叉搜索树的特点
        // 比如当前parent.key == 7; key == 8 /6 只需要判断他在左右
        if (key < parent.key) {
            parent.left = addNode;
        } else if (key > parent.key) {
            parent.right = addNode;
        }

        // 2、key 不存在 新增


    }

    // 查找关键字的前驱值
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;

        while (p != null) {

            if (key > p.key) {
                // 只要向右走 就说明 他是自作而来的祖先
                ancestorFromLeft = p;
                p = p.right;
            } else if (key < p.key) {
                p = p.left;
            } else {
                break;
            }
        }

        // 退出循环时 找到了节点 p
        if (p == null) {
            return null;
        }
        // 1、有左子树
        if (p.left != null) {
            return max(p);
        }

        // 2、没有左子树 此时 离他最近的 自作而来的祖先就是他的前任
        if (ancestorFromLeft != null) {
            return ancestorFromLeft.value;
        }
        return null;
    }


    // 查找关键字的后继值
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key > p.key) {
                p = p.right;
            } else if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else break;


        }

        // 退出循环时 找到节点p
        if (p == null) {
            return null;
        }

        // 1、有柚子树
        if (p.right != null) {
            return min(p);
        }


        // 2、没有柚子树
        if (ancestorFromRight != null) {
            return ancestorFromRight.value;
        }

        return null;
    }

    // 根据关键点删除
    public Object delete(int key) {
        // 1、找到待删除节点
        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            if (key > p.key) {
                parent = p;
                p = p.right;
            } else if (key < p.key) {
                parent = p;
                p = p.left;
            } else break;
        }

        // 退出循环时 找到节点p
        // 退出循环时 找到节点p
        if (p == null) {
            return null;
        }

        // 删除操作
        if (p.left == null) {
            // 情况1
            shift(parent, p, p.right);
        } else if (p.right == null) {
            // 情况2
            shift(parent, p, p.left);
        } else {
            // 情况3
            // 3.1 找后继节点
            BSTNode s = p.right;
            // 3.2.1 找父亲
            BSTNode sP = p;
            while (s.left != null) {
                sP = s;
                s = s.left;
            }

            // 3.2 删除节点与后续将节点相邻： 被删除节点时后继节点的父亲
            if (sP != p) {// 不相邻
                // 不相邻
                // 3.2 删除节点与后续将节点不相邻
                // 3.2.1 处理后续节点的后事 即托孤
                shift(sP, s, s.right);
                s.right = p.right;

            }
            // 只有不相邻才需要托孤 而托孤之后 想不想理你的操作都一样的
            // 3.3 不相邻 ：此时将后继节点吐托孤给删除节点的父节点
            shift(parent, p, s);
            // 此时还要将被删除节点的两个孩子托孤给顶上来的后继节点
            s.left = p.left;

        }
        return null;
    }


    //

    /**
     * 递归删除
     *
     * @param node 被删除节点
     * @param key
     * @return 被删除的节点的孩子
     */
    private BSTNode doDelete(BSTNode node, int key) {
        if (node == null) {
            return null;
        }
        // 向左找
        if (key < node.key) {
            // 将执行删除操作后的返回值更新给被删除节点的父节点的孩子
            node.left = doDelete(node.left, key);
            return node;

        }
        // 向右找
        if (key > node.key) {
            node.right = doDelete(node.right, key);
            return node;
        }

        // 以上都是没找到进行递归操作
        // 找到了
        // 三种情况
        // 1、没有左孩子
        if (node.left == null) {
            return node.right;
        }

        // 2、没有右孩子
        if (node.right == null) {
            return node.left;
        }

        // 3、孩子都有 找后继节点 把后继节点换掉被删除节点 再建立后继节点与被删除节点的父子节点的关系 在递归里面 后继节点就是删剩下的 返回后继节点就行
        // 此时操作就变成了找后继节点
        // 从当前节点的柚子树里面找一个最小的节点 就是后继节点
        // 下面这段代码 只考虑了被删除节点与后继节点相邻情况
        // 1、相邻
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        // 2、不相邻 还得处理被删除节点的孩子
        // 此时 把后继节点当成被删除节点 递归起点是被删除节点的柚子节点
        // 删剩下的最后返回的就是被处理后的被删除节点的柚子树 将来这个柚子树要作为后继节点的柚子树
        s.right = doDelete(node.right, s.key);

        // 先删除 再把后继节点的左孩子赋值
        s.left = node.left;


        return s;


    }

    /**
     * 托孤方法
     *
     * @param parent
     * @param deleted
     * @param child
     */
    public void shift(BSTNode parent, BSTNode deleted, BSTNode child) {

        // 当被删除节点为根节点 此时 parent==null 将child成为新的根节点 注意 child 只有一个 左 或者 右
        if (parent == null) {
            root = child;
        }
        // 被删除节点在右边 托孤在右边 左边同理
        else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }

    }

    /**
     * 递归
     *
     * @param node
     * @param key
     * @return
     */
    // 递归
    private Object rec(BSTNode node, int key) {

        if (node == null) {
            return null;
        }
        // 向左找
        if (key < node.key) {
            return rec(node.left, key);
        }

        // 向右找
        else if (key > node.key) {
            return rec(node.right, key);
        }

        // 就是当前key
        else return node.value;

        // 没找到

    }

    // 递归实现查找最小节点
    private Object doMin(BSTNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.key;
        } else {
            return doMin(root.left);
        }

    }

    public static void main(String[] args) {

        BSTNode n1 = new BSTNode(1, "a");
        BSTNode n3 = new BSTNode(3, "c");
        BSTNode n2 = new BSTNode(2, "b", n1, n3);
        BSTNode n5 = new BSTNode(5, "e");
        BSTNode n7 = new BSTNode(7, "g");
        BSTNode n6 = new BSTNode(6, "f", null, n7);
        BSTNode root = new BSTNode(4, "d", n2, n6);

        BSTTree1 tree1 = new BSTTree1();
        tree1.root = root;
//        System.out.println(tree1.getByRec(0));
//        System.out.println(tree1.get(0));
//        System.out.println(tree1.min());
//        tree1.put(1,"c");
//        System.out.println(tree1.get(1));
//
//        System.out.println(tree1.get(10));
//        tree1.put(10,"w");
//        System.out.println(tree1.get(10));
//        System.out.println(tree1.successor(2));

        System.out.println(tree1.successor(2));
        tree1.delete(6);
        System.out.println(tree1.predecessor(4));


    }


}
