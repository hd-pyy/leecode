package src.t3_LinkedList.DoublyLinkedListSentinel;

public class test {
    public static void main(String[] args) {

        t3_LinkedList.DoublyLinkedListSentinel.DoublyLinkedListSentinel doublyLinkedListSentinel = new t3_LinkedList.DoublyLinkedListSentinel.DoublyLinkedListSentinel();
        doublyLinkedListSentinel.addFirst(13);
        doublyLinkedListSentinel.addFirst(14);
        doublyLinkedListSentinel.addFirst(15);
        doublyLinkedListSentinel.addLast(13);
        doublyLinkedListSentinel.addLast(14);
        doublyLinkedListSentinel.addLast(15);

        for (Integer i : doublyLinkedListSentinel) {
            System.out.println(i);
        }

    }
}
