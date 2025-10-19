public class ContarVogalEConsoante {

    public static void contarVogaisConsoantes(String s) {
        int vogais = 0, consoantes = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) vogais++;
            else if (c >= 'a' && c <= 'z') consoantes++;
        }
        System.out.println("Vogais: " + vogais + ", Consoantes: " + consoantes);
    }

}
