package t7_2tree;

public class TreeNode2 {
    public String val;
    public TreeNode2 left;
    public TreeNode2 right;

    public TreeNode2(String val) {
        this.val = val;
    }
    public TreeNode2(TreeNode2 left, String val, TreeNode2 right) {
        this.left = left;
        this.val = val;
        this.right = right;
    }


}
