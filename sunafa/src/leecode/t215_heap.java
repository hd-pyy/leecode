package src.leecode;

import src.t6_heap.MaxHeap;
import src.t6_heap.MinHeap;

import java.util.Arrays;

/**
 * 1、在小顶堆中放入前k个元素
 * 2、剩余元素
 *      ·<=顶堆元素 滤过
 *      ·>顶堆元素 替换顶堆元素
 */
public class t215_heap {


    public static int findKthLarge(int[] num, int k){
        MinHeap minHeap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            minHeap.offer(num[i]);
        }
        for (int i = k; i < num.length; i++) {
            if (num[i] > minHeap.peek()){
                minHeap.replace(num[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5,7,4,8};
        System.out.println(findKthLarge(arr, 1));

    }



}


