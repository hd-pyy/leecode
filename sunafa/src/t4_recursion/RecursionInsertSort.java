package t4_recursion;

import java.util.Arrays;

public class RecursionInsertSort {


    public static int[] search(int[] arr) {

        insert(arr, 0);
        return arr;
    }

    /**
     * @param arr 待排序数组
     * @param low 未排序数组低位1
     * @return
     */
    private static void insert(int[] arr, int low) {
        if (low == arr.length) {
            return;
        }

        int t = arr[low]; // 当前待插入元素
        int p = low - 1; // 已排序数组指针

        while (p >= 0 && arr[p] > t) {
            // 将当前指针所指向元素右移一位
            arr[p + 1] = arr[p];

            // 将当前指针左移
            p--;
        }

        // 循环结束后 p 指向的就是比 待插入元素小的值 那就应该插入到 p+1的位置处
        arr[p + 1] = t;


        // 每次递归 向右移一位
        insert(arr, low + 1);
    }

    public static void main(String[] args) {
        int[] a = {1, 34, 37, 65, 87, 98, 443, 567, 5245};
        int tar = 87;
        int[] search = search(a);
        System.out.println(Arrays.toString(search));
    }
}
