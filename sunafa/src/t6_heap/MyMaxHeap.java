package t6_heap;

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
        if (left < size && arr[left] > arr[max]) {
            max = left;
        }
        if (right < size && arr[right] > arr[max]) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    private void swap(int max, int parent) {
        int temp = arr[max];
        arr[max] = arr[parent];
        arr[parent] = temp;
    }

    // 获取堆顶元素
    public int peek() {
        return arr[0];
    }

    // 删除堆顶元素
    public int poll() {
        int top = arr[0];
        // 1、将堆顶与尾部进行交换
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    // 删除指定索引位置元素
    public int poll(int index) {
        int recurrent = arr[index];
        swap(index, size - 1);
        size--;
        down(index);
        return recurrent;
    }


    // 替换对顶元素
    public void replaced(int r) {
        arr[0] = r;
        down(0);
    }

    // 在底部添加一个元素
    // 将 offered元素上浮 带扩容
    public boolean offer(int offered) {
        if (size == arr.length) {
//            size++;
            arr = Arrays.copyOf(arr, arr.length + 1);
//            offer(offered);
        }
        up(offered);
        // 上浮成功
        size++;
        return true;
    }

    // 上浮
    public void up(int offered) {
        // child指针 一直上浮 当指针为零时 到达堆顶
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            // 当offered大于父元素 则交换
            if (offered > arr[parent]) {
                // 把父元素所在位置腾出来
                arr[child] = arr[parent];

            } else break;
            // 移动child指针到parent
            child = parent;
        }
        // 给child元素赋值
        arr[child] = offered;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 5, 6, 7};
        MyMaxHeap myMaxHeap = new MyMaxHeap(a);
        System.out.println(Arrays.toString(myMaxHeap.arr));
        myMaxHeap.replaced(10);
        System.out.println(Arrays.toString(myMaxHeap.arr));
        myMaxHeap.offer(2);
        System.out.println(Arrays.toString(myMaxHeap.arr));
        myMaxHeap.offer(22);
        System.out.println(Arrays.toString(myMaxHeap.arr));

    }

}
