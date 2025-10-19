public class InverterString {

    public static String inverter(String texto) {
        return new StringBuilder(texto).reverse().toString();
    }

    public static String inverterManual(String texto) {
        String invertida = "";
        for (int i = texto.length() - 1; i >= 0; i--) {
            invertida += texto.charAt(i);
        }
        return invertida;
    }

}
