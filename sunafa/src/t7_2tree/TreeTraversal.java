package t7_2tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeTraversal {

    public static void main(String[] args) {

        // 树
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );

        System.out.println("前序");
        preOrder(root);
        System.out.println();
        System.out.println("中序");
        midOrder(root);
        System.out.println();

        System.out.println("后序");
        postOrder(root);
        System.out.println();


        System.out.println("前序");
        preOrder2(root);
        System.out.println();

        System.out.println("后序");
        postOrder2(root);
        System.out.println();


        System.out.println("树遍历");
        TreeBianLi(root);
        System.out.println();
    }

    // 非递归实现 通过栈实现 前序 中序 只改sout即可
    private static void preOrder2(TreeNode root) {
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
    // 非递归实现 通过栈实现 后序
    // 后序需要先输出左右节点 再输出父节点 故 不可以直接先将父节点从栈中弹出
    private static void postOrder2(TreeNode root) {
        // 非递归实现
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode cur = root;
        TreeNode pop = null;// 记录上一次被弹出栈的节点
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
//                System.out.println("去：" + cur.val);
                stack.push(cur); // 压入栈 记录来时路
                cur = cur.left;
            } else {
                TreeNode peek = stack.peek();
                // 1、当前节点的右节点为空 则直接可以将当前节点 输出 并弹出栈
                if( peek.right  == null || pop == peek.right){
                    pop = stack.pop();
                    System.out.print(pop.val + "\t");

                }else {
                    cur = peek.right;
                }


            }

        }
    }

    // 完整的绕树一周
    private static void TreeBianLi(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;// 代表当前节点
        TreeNode pop = null;// 代表最近一次被弹出栈的节点

        List<Integer> preorder = new ArrayList<>();  // 前序遍历结果
        List<Integer> inorder = new ArrayList<>();   // 中序遍历结果
        List<Integer> postorder = new ArrayList<>(); // 后序遍历结果

        while (!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur);// 入栈
                // 待处理左子树
                preorder.add(cur.val);
                cur = cur.left;

            }
            // 左子树入栈完成
            else {
                // 栈顶
                TreeNode peek = stack.peek();

                // 没有柚子树
                if (peek.right == null){
                    inorder.add(peek.val);
                    // 直接出栈
                    pop = stack.pop();
                    postorder.add(pop.val);
                }
                // 柚子树处理完成
                else if (peek.right == pop){
                    pop = stack.pop();
                    postorder.add(pop.val);
                }
                // 以上都不满足 说明需要处理柚子树
                else {
                    cur = peek.right;
                    inorder.add(peek.val);
                }

            }
        }

        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }

    // 完整的绕树一周
    private static void TreeBianLi0(TreeNode root){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;// 代表当前节点
        TreeNode pop = null;// 代表最近一次被弹出栈的节点


        while (!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur);// 入栈
                // 待处理左子树
                cur = cur.left;

            }
            // 左子树入栈完成
            else {
                // 栈顶
                TreeNode peek = stack.peek();

                // 没有柚子树
                if (peek.right == null){
                    // 直接出栈
                    pop = stack.pop();
                }
                // 此时 柚子树处理完成
                else if (peek.right == pop){
                    pop = stack.pop();
                }
                // 以上都不满足 说明需要处理柚子树
                else {
                    cur = peek.right;
                }

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
