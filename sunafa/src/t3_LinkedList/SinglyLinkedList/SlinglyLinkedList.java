package t3_LinkedList.SinglyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class SlinglyLinkedList  implements Iterable<Integer>{
    private Node head = null; // 头指针




    /**
     * 节点类
     */
    private static class Node {
        int value;
        Node next;


        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }


    /**
     * 添加
     */
    public void add(int value){
        // 1、链表为空 head指向新节点
//        head = new Node(value, null);
        // 2、链表非空
        head = new Node(value, head);

    }

    /**
     * 遍历链表
     */
    public void loop(){
        Node p = head;
        while(p != null){
            System.out.println(p.value);
            p = p.next;

        }
    }

    /**
     * 遍历链表
     */
    public void loop2(Consumer<Integer> consumer){
        Node p = head;
        while(p != null){
            consumer.accept(p.value);
            p = p.next;

        }
    }


    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){
            Node p = head;
            @Override
            public boolean hasNext() { // 是否有下一个元素
                // 如果返回真则代表可以调用next方法
                return p!= null;
            }

            @Override
            public Integer next() {
                int v= p.value;
                p = p.next;
                return v;
//                p = p.next;
//                return p.value;
            }
        };

    }

}
