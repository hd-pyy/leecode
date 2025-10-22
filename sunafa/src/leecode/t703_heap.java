package leecode;

import src.t6_heap.MinHeap;

public class t703_heap {

    private src.t6_heap.MinHeap minHeap;

    public t703_heap(int k, int[] nums) {
        minHeap = new MinHeap(k);
        // 将数组元素添加
        for (int num : nums) {
            add(num);


        }
    }

    private int add(int val) {
        if (!minHeap.isFull()) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.replace(val);
        }
        return minHeap.peek();

    }

    public static void main(String[] args) {
        t703_heap test = new t703_heap(3, new int[]{4, 5, 8, 2});

        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));


    }


}
