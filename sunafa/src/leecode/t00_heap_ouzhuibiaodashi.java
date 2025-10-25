package leecode;

import t7_2tree.TreeNode2;

import java.util.Arrays;
import java.util.LinkedList;

/***
 * 原始数学式 (2-1)*3
 * 中缀表达式 （2-1）*3
 * 后缀/逆波兰表达式 21-3*
 *
 */
// 题目要求 将 后缀表达式 转化为 中缀表达式
    // 用找实现
    // 遇到数字进站 遇到运算符出站  建立节点关系后再入栈 是将运算符入栈

public class t00_heap_ouzhuibiaodashi {


    public TreeNode2 constructExTree(String[] tokens){
        LinkedList<TreeNode2> stack = new LinkedList<>();
        for (String token : tokens) {
             switch (token){
                 case "+","-","*","/"->{
                     TreeNode2 right = stack.pop();
                     TreeNode2 left = stack.pop();
                     TreeNode2 parent = new TreeNode2(token);
                     parent.left = left;
                     parent.right = right;
                     stack.push(parent);
                 }
                 default -> {
                     stack.push(new TreeNode2(token));

                 }
             }
        }
        return stack.peek();
    }
}
