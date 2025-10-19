public class ContarParEImpar {

    public static void contarParesImpares(int[] numeros) {
        int pares = 0, impares = 0;
        for (int n : numeros) {
            if (n % 2 == 0) pares++;
            else impares++;
        }
        System.out.println("Pares: " + pares + " | Ímpares: " + impares);
    }

}
