import java.util.Arrays;

public class OrdenarArray {

    public static void ordenarComArrays(int[] numeros) {
        Arrays.sort(numeros);
    }

    public static void ordenarBubble(int[] numeros) {
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = 0; j < numeros.length - 1 - i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }
        }
    }


}
