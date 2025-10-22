package src.t6_heap;

import java.util.Arrays;

/**
 * 大顶堆
 */
public class MaxHeap {
    public int[] array;
    int size;

    public MaxHeap(int cap) {
        this.array = new int[cap];
    }

    public MaxHeap(int[] array){
        this.array = array;
        this.size = array.length;
        heapify();
    }

    // 建堆
    private void heapify(){
        // 找到最后非叶子节点 (size >>> 1) - 1
        for (int i = size/2 -1; i >=0 ; i--) {
            // 下潜
            down(i);
        }
    }

    /**
     * 下潜
     * 与两个孩子较大者进行比较
     * @param parent 要下潜的索引值
     */
    private void down(int parent) {
        int left = parent*2 +1;
        int right = left +1;
        int max = parent;
        if (left < size && array[left]>array[max]){
            max = left;
        }
        if (right < size && array[right]>array[max]){
            max = right;
        }
        if (max != parent){
            // 找到了更大的孩子 交换 交换max与parent
            swap(max,parent);
            // 递归 下潜
            down(max);
        }
    }

    /**
     * 交换
     * @param max
     * @param parent
     */
    private void swap(int max,int parent){
        int t = array[max];
        array[max] = array[parent];
        array[parent] = t;

    }

    /**
     * 获取堆顶数据
     * @return 堆顶
     */
    public int peek(){
        return array[0];
    }


    /**
     * 删除堆顶元素
     * 1、交换顶 与 底
     * 2、下潜
     * @return
     */
    public int poll(){
        // 交换
        int top = array[0];
        swap(0,size-1);
        // 减小对
        size--;
        // 下潜
        down(0);
        return top;
    }

    /**
     * 删除指定位置元素
     * @param index
     * @return
     */
    public int poll(int index){
        // 交换
        int deleted = array[index];
        swap(index,size-1);
        // 减小对
        size--;
        // 下潜
        down(index);
        return deleted;
    }

    

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,7};
        MaxHeap maxHeap = new MaxHeap(arr);
        System.out.println(Arrays.toString(maxHeap.array));
//        int peek = maxHeap.peek();
//        System.out.println(peek);
//        int poll = maxHeap.poll();
//        System.out.println(poll);
//        System.out.println(Arrays.toString(maxHeap.array));
    }
}
