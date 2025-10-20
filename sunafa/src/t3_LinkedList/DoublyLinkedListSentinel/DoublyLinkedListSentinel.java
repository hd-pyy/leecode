package t3_LinkedList.DoublyLinkedListSentinel;


import java.util.Iterator;

public class DoublyLinkedListSentinel implements Iterable<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sential.next;

            @Override
            public boolean hasNext() {
                return p != sential;
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
     * 构造节点类
     */
    static class Node {
        Node prev;
        int value;
        Node next;

        /**
         * 全残构造函数
         *
         * @param prev
         * @param value
         * @param next
         */
        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    // 哨兵对象
    public final Node sential = new Node(null, 0, null);

    /**
     * 链表构造方法 哨兵初始化
     */
    public DoublyLinkedListSentinel() {
        sential.prev = sential;
        sential.next = sential;
    }

    public void addFirst(int value) {
        Node prev = sential;
        Node next = sential.next;
        Node now = new Node(prev, value, next);
        prev.next = now;
        next.prev = now;

    }

    public void addLast(int value){
        Node prev = sential.prev;
        Node next = sential;
        Node now = new Node(prev,value,next);
        prev.next = now;
        next.prev = now;
    }

    public void removeFirst(){

        Node prev = sential;
        Node now = sential.next;
        if ( now == sential){
            throw new RuntimeException("哨兵不能被删除");
        }
        Node next = now.next;
        prev.next = next;
        next.prev = prev;

    }
}
