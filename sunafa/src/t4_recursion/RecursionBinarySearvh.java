package t4_recursion;

public class RecursionBinarySearvh {


    public static int search(int[] arr, int target){
        return f(arr,target,0,arr.length);
    }

    private static int f(int[] arr, int target, int i, int j){
        if ( i > j){
            return -1;
        }

        int m = (i+j) >>> 1;

        if(target > arr[m]){
            f(arr,target,m+1,j);
        }
        if (target < arr[m]){
            f(arr,target,i,m-1);
        }
        else return m;


        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,34,37,65,87,98,443,567,5245};
        int tar = 87;
        int search = search(a, tar);
        System.out.println(search);
    }
}
