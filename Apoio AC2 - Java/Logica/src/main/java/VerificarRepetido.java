import java.util.HashSet;
import java.util.Set;

public class VerificarRepetido {

    public static boolean temRepetido(int[] numeros) {
        Set<Integer> vistos = new HashSet<>();
        for (int n : numeros) {
            if (!vistos.add(n)) return true;
        }
        return false;
    }

    public static boolean temRepetidoManual(int[] numeros) {
        for (int i = 0; i < numeros.length; i++) {
            for (int j = i + 1; j < numeros.length; j++) {
                if (numeros[i] == numeros[j]) return true;
            }
        }
        return false;
    }


}
