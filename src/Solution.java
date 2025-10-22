//
//public class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//
//    // 添加反序列化方法
//    public static ListNode deserialize(String data) {
//        if (data == null || data.equals("[]"))
//            return null;
//
//        // 去除方括号和空格
//        data = data.replace("[", "").replace("]", "").replace(" ", "");
//        if (data.isEmpty())
//            return null;
//
//        String[] values = data.split(",");
//        ListNode dummy = new ListNode(0);
//        ListNode current = dummy;
//
//        for (String value : values) {
//            current.next = new ListNode(Integer.parseInt(value));
//            current = current.next;
//        }
//
//        return dummy.next;
//    }
//}
//
//class Solution {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//        ListNode n1 = new ListNode(0);// 写0 是为了创建一个非空头节点 后面返回时 需要从下一节点开始
//        ListNode res = n1; // n1 为当前节点值， res 为整个链表 由于n1 头节点为0 所以res也是 最后返回时 需要从头节点的下一个节点开始
//        int carry = 0;// 储存进位
//        while (l1 != null || l2 != null || carry != 0) {
//            int var1 = (l1 != null) ? l1.val : 0;
//            int var2 = (l2 != null) ? l2.val : 0;
//
//            int sum = var1 + var2 + carry; // carry 是前一个节点传过来的进位值
//            // 当只剩进位值时 当前值就等于进位值
//            int digit = sum % 10; // 当前位
//            carry = sum / 10; // 进位
//
//
//            // 创建新节点 并移动到链表的尾部
//            n1.next = new ListNode(digit);
//            n1 = n1.next;
//
//            if (l1 != null)
//                l1 = l1.next;
//            if (l2 != null)
//                l2 = l2.next;
//
//        }
//        return res.next;
//    }
//}
