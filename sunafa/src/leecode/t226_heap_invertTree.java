package leecode;

import t7_2tree.TreeNode;

// 把左右孩子进行交换
public class t226_heap_invertTree {

    public static void invert(TreeNode node){
        if (node == null){
            return;
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;

        invert(node.left);
        invert(node.right);

    }
}
