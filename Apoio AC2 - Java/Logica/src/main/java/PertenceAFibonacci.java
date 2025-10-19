public class PertenceAFibonacci {

    public static boolean pertenceFibonacci(int num) {
        int a = 0, b = 1;
        while (a <= num) {
            if (a == num) return true;
            int temp = a + b;
            a = b;
            b = temp;
        }
        return false;
    }

    public static boolean pertenceFibonacciRecursivo(int num, int a, int b) {
        if (a == num) return true;
        if (a > num) return false;
        return pertenceFibonacciRecursivo(num, b, a + b);
    }

}
