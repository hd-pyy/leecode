package t7_2tree;

import java.util.LinkedList;

public class TreeTraversal {

    public static void main(String[] args) {

        // 树
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );

        preOrder(root);
        System.out.println();
        midOrder(root);
        System.out.println();

        postOrder(root);
        System.out.println();


        extracted(root);

    }

    // 非递归实现 通过栈实现
    private static void extracted(TreeNode root) {
        // 非递归实现
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
//                System.out.println("去：" + cur.val);
                stack.push(cur); // 压入栈 记录来时路
                cur = cur.left;
            } else {
                // 左边走到头
                TreeNode pop = stack.pop();
//                System.out.println("回：" + pop.val);
                System.out.print(pop.val + "\t");
                cur = pop.right;
            }

        }
    }

    // 前序遍历
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "\t");

        preOrder(node.left);  // 左子树
        preOrder(node.right);  // 右子树
    }

    // 中序遍历
    public static void midOrder(TreeNode node) {
        if (node == null) {
            return;
        }


        midOrder(node.left);  // 左子树
        System.out.print(node.val + "\t");
        midOrder(node.right);  // 右子树
    }

    // 前序遍历
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);  // 左子树
        postOrder(node.right);  // 右子树
        System.out.print(node.val + "\t");

    }
}
