package src.t5_queue;

import java.util.Iterator;

public class test {
    public static void main(String[] args) {

        LikedListQueue<Object> queue = new LikedListQueue<>();
        queue.offer(1);
        queue.offer(1);
        queue.offer(1);
        queue.offer(1);
        queue.offer(1);
        queue.offer(1);

        // 方法1：使用迭代器遍历
        System.out.println("方法1 - 使用迭代器遍历:");
        Iterator<Object> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.print(element + " ");
        }

        // inEmpty
        System.out.println(queue.isEmpty());

    }
}
