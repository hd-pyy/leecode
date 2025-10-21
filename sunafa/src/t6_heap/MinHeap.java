package src.t6_heap;

import java.util.Arrays;

/**
 * 小顶堆
 */
public class MinHeap {
    public int[] array;
    int size;

    public MinHeap(int cap) {
        this.array = new int[cap];
    }

    public MinHeap(int[] array){
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
     * 上浮操作
     * @param offered 要插入的值
     */
    private void up(int offered){
        int child = size; // 新元素将要插入的位置
        while (child > 0){
            int parent = (child - 1) / 2;
            if(offered < array[parent]){
                array[child] = array[parent]; // 父节点下移
                child = parent; // 孩子指针上移
            }else {
                break;
            }
        }
        array[child] = offered; // 最终位置
    }

    /**
     * 添加元素到堆中
     * @param offered 要添加的值
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            // 堆已满，可以在这里实现扩容
            return false;
        }
        up(offered); // 上浮调整
        size++;      // 增加堆大小
        return true;
    }

    /**
     * 带扩容的offer方法
     * @param offered 要添加的值
     */
    public void offerWithResize(int offered) {
        if (size == array.length) {
            // 扩容
            int newCapacity = array.length * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
        up(offered); // 上浮调整
        size++;      // 增加堆大小
    }
    /**
     * 替换堆顶元素
     * 比先poll再offer更高效，只需要一次下潜操作
     * @param newValue 新的堆顶值
     * @return 被替换的堆顶元素
     */
    public int replace(int newValue) {
        if (size == 0) {
            throw new IllegalStateException("堆为空");
        }
        int oldValue = array[0];
        array[0] = newValue;
        down(0); // 从堆顶开始下潜调整
        return oldValue;
    }

    /**
     * 下潜
     * 与两个孩子较小者进行比较（小顶堆）
     * @param parent 要下潜的索引值
     */
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int min = parent;
        if (left < size && array[left] < array[min]){
            min = left;
        }
        if (right < size && array[right] < array[min]){
            min = right;
        }
        if (min != parent){
            // 找到了更小的孩子 交换
            swap(min, parent);
            // 递归 下潜
            down(min);
        }
    }

    /**
     * 交换
     * @param i 第一个索引
     * @param j 第二个索引
     */
    private void swap(int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * 获取堆顶数据
     * @return 堆顶
     */
    public int peek(){
        if (size == 0) {
            throw new IllegalStateException("堆为空");
        }
        return array[0];
    }

    /**
     * 删除堆顶元素
     * 1、交换顶 与 底
     * 2、下潜
     * @return 删除的元素
     */
    public int poll(){
        if (size == 0) {
            throw new IllegalStateException("堆为空");
        }
        // 交换
        int top = array[0];
        swap(0, size - 1);
        // 减小堆大小
        size--;
        // 下潜
        down(0);
        return top;
    }

    /**
     * 删除指定位置元素
     * @param index 要删除的索引
     * @return 删除的元素
     */
    public int poll(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("索引越界");
        }
        // 交换
        int deleted = array[index];
        swap(index, size - 1);
        // 减小堆大小
        size--;
        // 下潜
        down(index);
        return deleted;
    }

    /**
     * 获取堆的大小
     * @return 堆中元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断堆是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5,7,4,8};
        MinHeap minHeap = new MinHeap(arr);
        System.out.println("初始堆: " + Arrays.toString(minHeap.array));

        int peek = minHeap.peek();
        System.out.println("堆顶元素: " + peek);

        minHeap.offer(3);
        System.out.println("添加3后: " + Arrays.toString(Arrays.copyOf(minHeap.array, minHeap.size)));

        int poll = minHeap.poll();
        System.out.println("删除的元素: " + poll);
        System.out.println("删除后堆: " + Arrays.toString(Arrays.copyOf(minHeap.array, minHeap.size)));

        // 测试逐个添加
        MinHeap heap2 = new MinHeap(10);
        heap2.offer(5);
        heap2.offer(3);
        heap2.offer(8);
        heap2.offer(1);
        System.out.println("逐个添加后的堆: " + Arrays.toString(Arrays.copyOf(heap2.array, heap2.size)));
    }
}