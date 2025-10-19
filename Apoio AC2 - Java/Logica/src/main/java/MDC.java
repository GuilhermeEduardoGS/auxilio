public class MDC {
// Miaior divisor comum
    public static int mdc(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int mdcManual(int a, int b) {
        int menor = Math.min(a, b);
        int mdc = 1;
        for (int i = 1; i <= menor; i++) {
            if (a % i == 0 && b % i == 0) {
                mdc = i;
            }
        }
        return mdc;
    }

}
