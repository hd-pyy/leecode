package leecode;

import t7_2tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class t104_heap_maxDepth {


    // 递归实现
    public int maxDepth_recursion(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.right == null && node.left == null) {
            return 1;
        }

        int rd = maxDepth_recursion(node.right);
        int ld = maxDepth_recursion(node.left);

        //  +1 就是每一层的加一
        return Integer.max(rd, ld) + 1;
    }

    // 后序排序实现
    public int maxDepth(TreeNode node) {
        TreeNode cur = node;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int max = 0;// 栈的高度
        while (cur != null && !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                if (stack.size() > max) {
                    max = stack.size();
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
        return max;
    }

    // 层序遍历
    public int maxDepth2(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int dep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                System.out.println(poll.val + "\t");
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            dep++;
        }
        return dep;
    }
}
