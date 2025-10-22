package t2_DynamicArr;

import java.util.Iterator;

/**
 * 动态数组
 */
public class DynamicArr implements Iterable<Integer>{
    private int size = 0;
    private int cap = 8;
    private int[] arr = new int[cap];


    /**
     * 在最后位置 加入新值
     *
     * @param element
     */
    public void addLast(int element) {
//        arr[size] = element;
//        size++;
        insert(size,element);
    }

    /**
     * 选择位置插入
     */
    public void insert(int index, int element) {

        if ( index >= 0 && index < size){
            // 从原始数组arr 索引 index 开始拷贝到新数组arr 索引 index +1 拷贝 size - index 个元素
            System.arraycopy(arr, index, arr, index + 1, size - index);


        }
        arr[index] = element;
        size++;
    }

    public DynamicArr() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public DynamicArr(int size, int cap, int[] arr) {
        this.size = size;
        this.cap = cap;
        this.arr = arr;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Integer next() {
                return arr[i++];
            }
        };
    }
}
