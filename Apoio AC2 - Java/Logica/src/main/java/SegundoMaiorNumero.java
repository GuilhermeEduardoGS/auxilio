public class SegundoMaiorNumero {

    public static int segundoMaior(int[] arr) {
        int maior = Integer.MIN_VALUE, segundo = Integer.MIN_VALUE;
        for (int n : arr) {
            if (n > maior) {
                segundo = maior;
                maior = n;
            } else if (n > segundo && n != maior) {
                segundo = n;
            }
        }
        return segundo;
    }

}
