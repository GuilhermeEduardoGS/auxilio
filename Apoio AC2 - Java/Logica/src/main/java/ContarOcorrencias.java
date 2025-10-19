public class ContarOcorrencias {

    public static int contarOcorrencia(String texto, char letra) {
        int contador = 0;
        for (char c : texto.toCharArray()) {
            if (c == letra) contador++;
        }
        return contador;
    }

}
