package leecode;

import t7_2tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class t111_heap_minDepth {


    // 递归实现
    public int minDepth_recursion(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.right == null && node.left == null) {
            return 1;
        }

        int rd = minDepth_recursion(node.right);
        int ld = minDepth_recursion(node.left);
        if (rd == 0) {
            return ld + 1;
        }
        if (ld == 0) {
            return rd + 1;
        }
        //  +1 就是每一层的加一
        return Integer.min(rd, ld) + 1;
    }

    // 后序排序实现
    public int minDepth(TreeNode node) {
        TreeNode cur = node;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int min = 0;// 栈的高度
        while (cur != null && !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                if (stack.size() > min) {
                    min = stack.size();
                }
                cur = cur.left;
            } else {
                TreeNode peek = stack.peek();
                // 柚子树为Null
                if (peek.right == null) {
                    pop = stack.pop();
                } else if (peek.right == pop) {
                    pop = stack.pop();
                } else {
                    cur = peek.right;
                }
            }
        }
        return min;
    }

    // 层序遍历
    // 遇到的第一个叶子结点 就是最小深度
    // 左右节点均为null 则是 叶子结点
    public int minDepth2(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int dep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            dep++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.right == null && poll.left == null) {
                    return dep;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return dep;
    }
}
