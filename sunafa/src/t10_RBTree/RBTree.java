package t10_RBTree;


public class RBTree {

    enum Color {
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        int key;
        Object val;
        Node left;
        Node right;
        Node parent;//父节点
        Color color = Color.RED;

        public Node(int key, Object val) {
            this.key = key;
            this.val = val;
        }


        // 是否是左孩子
        boolean isLC() {
            return parent != null && parent.left == this;
        }

        // 赵叔叔
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLC()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        // 找兄弟
        Node bro() {
            if (parent == null) {
                return null;
            }
            if (this.isLC()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        // 判断是R B
    }


    // 判断 R B
    boolean isRed(Node node) {
        return node != null && node.color == Color.RED;

    }

    boolean isBlack(Node node) {
        return !isRed(node);
    }

    // 右旋
    private void rightRotate(Node pink) {
        // 0、先拿到粉色的爹
        Node pparent = pink.parent;
        // 1、换爹
        Node yellow = pink.left;
        Node green = yellow.right;

        // 2、给自己的爹重新赋值
        // 由于grenn 可能为 null 所以需要单独判断
        if (green != null) {
            green.parent = pink;

        }
        yellow.right = pink;
        yellow.parent = pink.parent;
        pink.left = green;
        pink.parent = yellow;
        // 3、还要为粉色的原始爹找个新儿子黄色
        if (pparent == null) {
            root = yellow;
        } else if (pparent.left == pink) {
            pparent.left = yellow;
        } else {
            pparent.right = yellow;
        }


    }

    // pyy的右旋
    private void myRR(Node n) {
        // 两个节点与父节点
        Node parent = n.parent;
        Node nL = n.left;
        Node nLR = nL.right;

        // 找爹
        if (nLR != null) {
            nLR.parent = n;
        }
        n.parent = nL;
        nL.parent = parent;

        // 找儿子
        n.left = nLR;
        nL.right = n;
        if (parent == null) {
            root = nL;
        } else if (parent.right == n) {
            parent.right = nL;
        } else {
            parent.left = nL;
        }


    }

    // 左旋
    private void leftRotate(Node n) {
        // 0、三个节点
        Node parent = n.parent;
        Node nR = n.right;
        Node nRL = nR.left;

        // 1、换爹
        if (nRL != null) {
            nRL.parent = n;
        }
        n.parent = nR;
        nR.parent = parent;

        // 2、换儿子
        n.right = nRL;
        nR.left = n;
        if (parent == null) {
            root = nR;
        } else if (parent.left == n) {
            parent.left = nR;
        } else {
            parent.right = nR;
        }


    }

    void fixRedRed(Node x) {
        // case 1 插入点是根节点 变黑即可
        if (x == root) {
            x.color = Color.BLACK;
            return;
        }
        // case 2 插入点节点父亲是黑丝 无需处理
        if (isBlack(x.parent)) {
            return;
        }
        // case 3 插入点叔叔是红色（执行到这里 由case 2 可知 父亲也为红色） 由于默认为红色 此时触发红红相邻
        Node parent = x.parent;
        Node grandparent = parent.parent;
        Node uncle = x.uncle();
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandparent.color = Color.RED;
            // 祖父可能会触发红红异常
            fixRedRed(grandparent);
            return;
        }

        // case 4 插入点父亲为红色 叔叔为黑丝 执行到此处 叔叔一定是黑色了（叔叔为Nil 并不存在 ）
        // 4.1 LL 父亲 与 插入 都是左孩子
        if (parent.isLC() && x.isLC()) {
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        }// 4.2 父亲为左 插入为右
        else if (parent.isLC() && !x.isLC()) {
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(parent);
            rightRotate(grandparent);
        }// 4.3 RR
        else if (!parent.isLC() && !x.isLC()) {
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        }// 4.4 RL
        else {
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(parent);
            leftRotate(grandparent);
        }


    }
    // 插入

    /**
     * 正常增、遇到红红不平衡进行调整
     *
     * @param key
     * @param val
     */
    public void put(int key, Object val) {
        Node p = root;
        Node parent = null;
        // 找到空位
        while (p != null) {
            // 每次循环前 记录爹
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                // 找到了当前key 做更新
                p.val = val;
            }
        }
        // 退出循环的话 就是找到了空位  做插入
        // 创建一个新的节点对象8
        Node inserted = new Node(key, val);

        // 父亲找儿子
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            // 儿子找父亲
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            // 儿子找父亲
            inserted.parent = parent;
        }
        // 找父亲放在里面是因为根节点没有父亲 如果不另外考虑这种情况 会报空指针的


    }


    // 删除
    public void remove(int key) {
        Node del = find(key);
        if (del == null) {
            return;
        }

        doRemove(del);
    }

    // 递归删除
    private void doRemove(Node del) {
        Node s = findReplace(del);// rep
        Node parent = del.parent;
        // 表示没有孩子
        if (s == null) {
            // case 1 ：删除的是根节点
            if (del == root) {
                root = null;
            } else {
                // case 2.1 删除的不是根节点 而且没有孩子
                // 判断被删除节点是父亲节点的左/右孩子 将 父亲节点的 左/右孩子 值为空
                if (del.isLC()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                // 并且将被删除的父亲节点也只为空 有助于垃圾回收
                del.parent = null;
            }
            return;
        }
        // 只有一个孩子
        if (del.left == null || del.right == null) {
            // case 1
            if (del == root) {
                root.val = s.val;
                root.key = s.key;
                root.left = null;
                root.right = null;
            } else {
                // case 2.1 删除的不是根节点 有一个孩子
                // 同样的 判断被删除是父亲的左右 将孩子托孤给父亲左右
                if (del.isLC()) {
                    parent.left = s;
                } else {
                    parent.right = s;
                }
                // 并且给孩子找新爹
                s.parent = parent;
                del.parent = null;
                del.right = null;
                del.left = null;

            }

            return;
        }

        // 此时有两个孩子 李代桃僵 先将待删除节点与后继节点的key 和 val 都交换 然后待删除节点其实变成了原来的后继节点

        int key = del.key;
        del.key = s.key;
        s.key = key;

        Object val = del.val;
        del.val = s.val;
        s.val = val;

        // 交换好之后 要删除的其实是后继节点 s 而 s 要么有一个孩子 要么没有孩子
        doRemove(s);


    }

    // 找到删除节点
    private Node find(int key) {
        Node p = root;

        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                // 找到了 key = p.key
                return p;
            }
        }

        // 循环完了都还没找到
        return null;
    }

    // 查找山剩下的节点
    private Node findReplace(Node del) {
        // 1、当前节点为叶子结点 即没有孩子
        if (del.left == null && del.right == null) {
            return null;
        }

        // 2、只有一个孩子
        if (del.right == null) {
            return del.left;
        }
        if (del.left == null) {
            return del.right;
        }

        // 3、两个个孩子都有
        // 3.1 找后继节点
        Node s = del.right;
        while (s.left != null) {
            s = s.left;
        }
        // 此时找到后继节点
        return s;
    }
}
