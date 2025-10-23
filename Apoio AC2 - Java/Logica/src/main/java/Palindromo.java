import java.util.ArrayList;
import java.util.List;

public class Palindromo {

    public static boolean ehPalindromo(String palavra) {
        String invertida = new StringBuilder(palavra).reverse().toString();
        return palavra.equalsIgnoreCase(invertida);
    }

    public static boolean ehPalindromoManual(String palavra) {
        palavra = palavra.toLowerCase();
        for (int i = 0; i < palavra.length() / 2; i++) {
            if (palavra.charAt(i) != palavra.charAt(palavra.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean ehPalindromoCom2For(String palavra){
        palavra = palavra.toLowerCase();
        List<Character> texto = new ArrayList<>();
        List<Character> textoInvertido = new ArrayList<>();

        for (int i = 0; i < palavra.length(); i++){
            texto.add(palavra.charAt(i));
        }

        for (int j = palavra.length() - 1; j >= 0; j--){
            textoInvertido.add(palavra.charAt(j));
        }

        return texto.equals(textoInvertido);
    }

    public static void main(String[] args) {
        String[] palavras = {
                "arara",
                "reviver",
                "java",
                "Ana",
                "SubiNoOnibus",
                "palindromo"
        };

        for (String palavra : palavras) {
            System.out.println("Testando: " + palavra);
            System.out.println("ehPalindromo:         " + ehPalindromo(palavra));
            System.out.println("ehPalindromoManual:   " + ehPalindromoManual(palavra));
            System.out.println("ehPalindromoCom2For:  " + ehPalindromoCom2For(palavra));
            System.out.println("----------------------------");
        }
    }

}
