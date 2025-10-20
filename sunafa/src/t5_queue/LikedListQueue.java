package src.t5_queue;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Consumer;

public class LikedListQueue<E> implements Queue<E>, Iterable<E> {


    public static class Node<E> {
        E value;
        Node<E> next;


        public Node(E value, Node<E> next) {
            this.next = next;
            this.value = value;
        }
    }

    Node<E> head = new Node<>(null, null);
    Node<E> tail = head;

    public LikedListQueue() {
        tail.next = head;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {

        return head == tail;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }


    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(E value) {

        Node<E> added = new Node<>(value, head);
        tail.next = added;
        tail = added;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
