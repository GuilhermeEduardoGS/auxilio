public class NumeroPerfeito {

    public static boolean ehPerfeito(int n) {
        int soma = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) soma += i;
        }
        return soma == n;
    }

    public static boolean ehPerfeitoManual(int n) {
        int soma = 0;
        System.out.print("Divisores de " + n + ": ");
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                soma += i;
            }
        }
        System.out.println();
        return soma == n;
    }

}
