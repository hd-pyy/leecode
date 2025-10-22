package t2_DynamicArr;

public class DynamicArrTest {
    public static void main(String[] args) {
        DynamicArr dynamicArr = new DynamicArr();
        dynamicArr.addLast(12);
        dynamicArr.addLast(12);
        dynamicArr.addLast(12);
        dynamicArr.addLast(12);
        dynamicArr.insert(0,11);
        int[] arr = dynamicArr.getArr();
        for (int i : arr) {
            System.out.println(i);
        }

        /**
         * 迭代器 增强for循环
         */
        for (Integer element : dynamicArr) {
            System.out.println(element);

        }
    }
}
