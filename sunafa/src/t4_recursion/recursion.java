package t4_recursion;

public class recursion {

    public static int f(int n){
        if (n == 1){
            return 1;
        }

        return f(n-1)*n;
    }

    public static void main(String[] args) {
        System.out.println(f(5));
    }

}
