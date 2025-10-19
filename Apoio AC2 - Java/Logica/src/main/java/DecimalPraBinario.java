public class DecimalPraBinario {

    public static String decimalParaBinario(int n) {
        String binario = "";
        while (n > 0) {
            binario = (n % 2) + binario;
            n /= 2;
        }
        return binario.isEmpty() ? "0" : binario;
    }

}
