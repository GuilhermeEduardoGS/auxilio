public class ContarVogais {

    public static int contarVogais(String texto) {
        int contador = 0;
        String vogais = "aeiouAEIOU";
        for (char c : texto.toCharArray()) {
            if (vogais.indexOf(c) != -1) contador++;
        }
        return contador;
    }

    public static int contarVogaisManual(String texto) {
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                    c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                contador++;
            }
        }
        return contador;
    }

}
