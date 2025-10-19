public class NumeroPalindromo {

    public static boolean numeroPalindromo(int n) {
        int original = n, reverso = 0;
        while (n > 0) {
            reverso = reverso * 10 + (n % 10);
            n /= 10;
        }
        return original == reverso;
    }

}
