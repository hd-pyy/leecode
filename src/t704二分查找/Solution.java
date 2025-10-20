package t704二分查找;

public class Solution {
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2));
    }

    /**
     * 超时
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {

        int i = 0;
        int j = nums.length;
        // 注意 这地方超时 是写错了 没有灯号
        while (i < j) {
            int m = (i + j) >>> 1;

            if (target < nums[m]) {
                j = m;
            } else if (nums[m] < target) {
                i = m + 1;
            } else if (target == nums[m]) {
                return m;
            }

        }
        return -1;

    }

    public static int searchRight(int[] nums, int target) {

        int i = 0;
        int j = nums.length;
        while (1 < j -i) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m;
            } else
                i = m ;


        }
        return (nums[i] == target) ? i :-1;

    }
}
