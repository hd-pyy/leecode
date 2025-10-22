package t1_BinarySearch;

public class BinarySearch {


    public static void main(String[] args) {
        int[] a = {1, 3, 4, 55, 98, 123, 123, 241, 434, 645, 35521, 232320};
        int res = bsRightMostUp(a, 214);
        System.out.println(res);

    }

    public static int bs(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;

        // 注意 一定是等于号 因为找的就是取等的索引 如果写小于号 会漏掉 i = j = m 的情况
        while (i <= j) {

            int m = (i + j) >>> 1;

            // 当 target 大于 中间值
            if (target > arr[m]) {
                i = m + 1;

            } else if (target < arr[m]) {
                j = m - 1;
            } else if (target == arr[m]) {
                return m;
            }


        }


        return -1;
    }

    // 当数字过大时 超过了Integer的最大范围 数字会由无符号转为
    // 因此需要改进
    public static int bs2(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;

        // 注意 一定是等于号 因为找的就是取等的索引 如果写小于号 会漏掉 i = j = m 的情况
        while (i <= j) {

            // 改进 无符号右移一位就是除二
//            int m = (i + j)/2;
            int m = (i + j) >>> 1;

            // 当 target 大于 中间值
            if (target > arr[m]) {
                i = m + 1;

            } else if (target < arr[m]) {
                j = m - 1;
            } else if (target == arr[m]) {
                return m;
            }
        }
        return -1;
    }

    // 改进 左闭右开 j 永远不可能成为索引 只是作为边界  不参与计算 将 j= m-1 改为 j= m 可以省去一定的计算量
    public static int bs3(int[] arr, int target) {

        int i = 0;
        int j = arr.length;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (target > arr[m]) {
                i = m + 1;
            } else if (target < arr[m]) {
                j = m;
            } else if (target == arr[m]) {
                return m;
            }
        }
        return -1;
    }

    // 改进
    public static int bs4(int[] arr, int target) {

        int i = 0;
        int j = arr.length;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m;
            } else if (arr[m] < target) {
                i = m + 1;
            } else
                return m;
        }
        return -1;
    }


    // 改进
    public static int bs5(int[] arr, int target) {

        int i = 0;
        int j = arr.length;
        // 改进  j -i 表示带查找的元素个数
        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        if (arr[i] == target) return i;
        else return -1;
    }

    // 查找与目标相等的最左侧的值
    public static int bsLeftMost(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;
        int candidate = -1;
        while (i <= j) {

            int m = (i + j) >>> 1;

            if (target > arr[m]) {
                i = m + 1;

            } else if (target < arr[m]) {
                j = m - 1;
            } else if (target == arr[m]) {
                candidate = m;
                j = j - 1;
            }
        }
        return candidate;
    }

    /**
     * 大于等于目标的最左侧的位置
     *
     * @param arr    带查找数组
     * @param target
     * @return
     */
    public static int bsLeftMostUp(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {

            int m = (i + j) >>> 1;

            if (target > arr[m]) {
                i = m + 1;

            } else if (target <= arr[m]) {
                j = m - 1;
            } else if (target == arr[m]) {
                j = j - 1;
            }
        }
        return i;
    }


    // 改进
    public static int bsRightMost(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;
        int candidate = -1;
        while (i <= j) {

            int m = (i + j) >>> 1;

            if (target > arr[m]) {
                i = m + 1;

            } else if (target < arr[m]) {
                j = m - 1;
            } else if (target == arr[m]) {
                candidate = m;
                i = i + 1;
            }
        }
        return candidate;
    }

    // 改进
    public static int bsRightMostUp(int[] arr, int target) {

        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i -1 ;
    }
}
