public class Fatorial {

    public static long fatorial(int n) {
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static long fatorialRecursivo(int n) {
        if (n == 0 || n == 1) return 1;
        return n * fatorialRecursivo(n - 1);
    }

}
