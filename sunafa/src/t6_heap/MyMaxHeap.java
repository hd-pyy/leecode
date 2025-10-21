package src.t6_heap;

import java.util.Arrays;

public class MyMaxHeap {
    int size;
    public int[] arr;


    public MyMaxHeap(int size) {
        this.size = size;
    }

    public MyMaxHeap(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
        heapify();
    }

    // 建堆
    private void heapify() {
        int parent = size / 2 - 1;
        for (int i = parent; i >= 0; i--) {
            down(i);
        }
    }

    // 下潜
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if ( left < size && arr[left] > arr[parent]){
            max = left;
        }
        if ( right < size && arr[right] > arr[parent]){
            max = right;
        }
        if ( max != parent){
            swap(max,parent);
            down(max);
        }
    }

    private void swap(int max,int parent){
        int temp = arr[max];
        arr[max] = arr[parent];
        arr[parent] = temp;
    }


    public static void main(String[] args) {
        int[] a =  {1,2,3,4,5,7};
        MyMaxHeap myMaxHeap = new MyMaxHeap(a);
        System.out.println(Arrays.toString(myMaxHeap.arr));
    }

}
