package src.t3_LinkedList.SinglyLinkedList;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.function.Consumer;

public class SLLTest {
    public static void main(String[] args) {

        t3_LinkedList.SinglyLinkedList.SlinglyLinkedList linkedList = new t3_LinkedList.SinglyLinkedList.SlinglyLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
//        linkedList.loop2(value ->{
//            System.out.println(value);
//        });
        for (Integer v : linkedList) {
            System.out.println(v);

        }



    }
}
