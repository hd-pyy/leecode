package t19删除倒数第几个节点;


import java.awt.*;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int i, int i1, int i2, int i3, int i4) {
    
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;


    }

    public int recursion(ListNode head, int n) {

        ListNode p = head;
        if (p == null) {
            return 0;
        }

        int nth = recursion(p.next, n);
        if (nth == n) {
            p.next = p.next.next;
        }

        return nth + 1;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1,2,3,4,5);
    }
}

