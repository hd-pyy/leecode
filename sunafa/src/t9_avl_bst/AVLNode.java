package t9_avl_bst;

public class AVLNode {
    int key;
    Object val;
    AVLNode left;
    AVLNode right;
    int hight = 1;

    public AVLNode(int key) {
        this.key = key;
    }

    public AVLNode(int key, Object val, AVLNode left, AVLNode right) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public AVLNode(int key, Object val) {
        this.key = key;
        this.val = val;
    }

    // 求任意节点的高度
    private int getHight(AVLNode node) {
        return node == null ? 0 : node.hight;
    }

    // 更新节点高度 当进行新增 删除操作时
    // 当前节点高度的左右子树最大高度 + 1
    private void updateHight(AVLNode node) {
        node.hight = Integer.max(getHight(node.left), getHight(node.right)) + 1;
    }

    // 求左右子树高度差

    // 平衡因子 左子树 - 柚子树

    /**
     *
     * @param node
     * @return 0 1 -1 平衡
     * else 不平衡
     * > 1 左高
     * < 1 右高
     */
    private int balanceFactor(AVLNode node) {
        return getHight(node.left) - getHight(node.right);
    }

    // 树失衡的四种情况

    /*
    LL

    LR

    RL

    RR
     */

    /**
     * 右旋
     * 1、上位
     * 2、换爹
     *
     * @param node 待旋转节点
     * @return 旋转后的新节点
     */
    private AVLNode rightRotate(AVLNode node) {
        AVLNode pleft = node.left;
        node.left = pleft.right;// 换爹
        // 给左节点右旋
        pleft.right = node;// 上位
        // 帮左节点原有的右节点找一个新爹 新爹就是原来的父节点 但是 左子树的一定小于原有的父节点 因此作为父节点的左孩子
        // 更新高度 必须先更新旋转后较低节点高度节点（原父节点） 再更新较高节点（父节点的左节点）
        updateHight(node);
        updateHight(pleft);
        return pleft;
    }

    // 左旋

    /**
     * 左旋
     * 1、换爹
     * 2、上位
     * 3、更新高度
     *
     * @param node 待旋转节点
     * @return 旋转后的新节点
     */
    private AVLNode leftRotate(AVLNode node) {
        AVLNode pRight = node.right;
        node.right = pRight.left;// 换爹
        pRight.left = node;// 上位
        updateHight(node);
        updateHight(pRight);
        return node;

    }

    // 先左子树左旋 再根节点右旋
    private AVLNode LR(AVLNode node) {
        // 先左子树左旋  旋转之后 上位之后的节点作为根节点的左孩子
        node.left = leftRotate(node.left);
        // 根节点右旋
        return rightRotate(node);

    }


    // 先右子树右旋 再根节点左 旋
    private AVLNode RL(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);

    }

    // 平衡
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }

        int bf = balanceFactor(node);

        if (bf > 1 && balanceFactor(node.left) >= 0) {//LL
            return rightRotate(node);
        } else if (bf > 1 && balanceFactor(node.left) < 0) {//LR
            return LR(node);
        } else if (bf < -1 && balanceFactor(node.right) > 0) {//RL
            return RL(node);
        } else if (bf < -1 && balanceFactor(node.right) <= 0) {//RR
            return rightRotate(node);
        }
        return node;
    }

    AVLNode root;

    public void put(int key, Object val) {
        root = doPut(root, key, val);
    }

    private AVLNode doPut(AVLNode node, int key, Object val) {
        // 找到空位 创建新节点 将新节点作为返回值返回
        if (node == null) {
            return new AVLNode(key, val);
        }

        // key 已存在 更新
        if (key == node.key) {
            node.val = val;
            return node;
        }

        // 继续递归查找
        if (key > node.key) {
            node.right = doPut(node.right, key, val);
        } else if (key < node.key) {
            node.left = doPut(node.left, key, val);
        }
        updateHight(node);
        return balance(node);

    }

    private void remove(int key) {
        root = doRemove(root, key);
    }

    /**
     * 递归实现删除
     *
     * @param node
     * @param key
     * @return 返回值代表删剩下的
     */
    private AVLNode doRemove(AVLNode node, int key) {
        // 1、出口
        if (node == null) {
            return null;
        }

        // 2、没找到key 继续递归查找
        if (key < node.key) {
            // doRemove以后返回的是删剩下的
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            // 3、找到key 上面两个if 走完了 就找到了
            // 没有孩子
            if (node.right == null && node.right == null) {
                // 当前节点左右孩子为空 而当前节点又被删除了 那直接返回null 给上一个调用处
                // 假设7->8  key == 8
                // 7 node.right = doRemove(node.right, key);
                // 8 return null
                // 7 node.right = null;
                return null;
            }
            // 只有一个孩子
            // 右孩子为空
            else if (node.right == null) {
                //右孩子为空
                node = node.left;
            } else if (node.left == null) {
                // 左孩子为空
                node = node.right;
            }

            // 两个孩子都有
            else {
                // 找到后继节点
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                // 退出循环时 找到后继节点 s
                // s后事处理 1、删除后继节点 此时 后继节点的孩子要处理好 而且返回值将作为后继节点的柚子树
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;


            }
        }
        // 4、更新高度
        updateHight(node);

        // 5、balance

        return balance(node);

    }
}
