public class MenorEMaior {

    public static void maiorMenor(int[] numeros) {
        int maior = numeros[0];
        int menor = numeros[0];
        for (int n : numeros) {
            if (n > maior) maior = n;
            if (n < menor) menor = n;
        }
        System.out.println("Maior: " + maior + " | Menor: " + menor);
    }

}
