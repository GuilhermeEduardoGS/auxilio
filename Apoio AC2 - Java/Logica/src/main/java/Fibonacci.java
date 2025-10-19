public class Fibonacci {

    public static void fibonacci(int termos) {
        int a = 0, b = 1;
        for (int i = 0; i < termos; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }

    public static int fibonacciRecursivo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
    }


}
