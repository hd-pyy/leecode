package t3_LinkedList.BiLinkedList;


import java.util.Iterator;

public class BiLinkedList implements Iterable<Integer> {

    /**
     * 头 尾 哨兵
     */
    private Node head;
    private Node tail;

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            // 从优质的地方开始遍历
            Node p = head.next;
            @Override
            public boolean hasNext() {
                /**
                 * return
                 *  1 代表执行下面的next
                 *   0 代表不执行
                 */
                return p != tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     * 节点类
     */
    private static class Node {
        Node prev;
        int value;
        Node next;

        /**
         * 构造函数
         *
         * @param prev
         * @param value
         * @param next
         */
        public Node(Node prev, int value, Node next) {
        }
    }

    /**
     * 初始化
     */
    BiLinkedList() {
        head = new Node(null, 0, null);
        tail = new Node(null, 1, null);
        head.next = tail;
        tail.prev = head;
    }


    /**
     * 根据索引查找节点
     *
     * @param index
     * @return
     */
    private Node getNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }


    /**
     * 在最前面插入值
     *
     * @param value
     */
    public void addFirst(int value) {
        insert(0,value);
    }

    /**
     * 根据索引位置 插入值
     *
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        // 1、找到索引位置前一个节点
        Node prev = getNode(index - 1);
        if (prev == null){
            throw new RuntimeException("当前输入不合法");
        }
        // 2、找到索引位置原来的节点 也就是 prev.next;
        Node next = prev.next;
        // 3、新建一个当前插入节点
        Node now = new Node(prev, value, next);

        // 4、上一节点的下一节点指向当前节点
        prev.next = now;

        // 5、下一节点的上一节点指向当前节点
        next.prev = now;


    }

    /**
     * 删除节点
     * @param index
     */
    public void remove(int index){
        // 1 找到上一届点
        Node prev = getNode(index - 1);
        // 2 找到下一节点
        Node next = prev.next.next;
        // 3 将上一节点的尾 指向下一节点
        prev.next = next;
        // 4 将下一节点的头 指向上一届点
        next.prev = prev;


    }


    /**
     * 在最后位置插入节点
     * @param value
     */
    public void addLast(int value){

        // 获取最后i一个节点
        Node last = tail.prev;

        // 新增节点
        Node now = new Node(last,value,tail);

        // 指定原始最后一个节点的下一节点为插入节点
        last.next = now;

        // 指定尾哨兵的前一个节点为插入节点
        tail.prev = now;

    }

    /**
     * 删除最后一个节点
     */
    public void removeLasst(){
        // 先判断整个链表是否为空 也就是只有两个哨兵 也就是尾哨兵的前一个节点为头哨兵
        if ( tail.prev == head){
            throw new RuntimeException("链表为空");
        }
        // 获取最后一个节点的前一个结点 也就是最后一个节点的前前任
        Node prev = tail.prev.prev;

        // 将前一个节点的后一个节点设置为尾哨兵
        prev.next = tail;

        // 将尾哨兵的前一个节点设置为 前一个结点
        tail.prev = prev;



    }


}
