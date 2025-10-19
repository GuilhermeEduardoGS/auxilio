public class InverterNumero {

    public static int inverterNumero(int n) {
        int inverso = 0;
        while (n != 0) {
            inverso = inverso * 10 + n % 10;
            n /= 10;
        }
        return inverso;
    }

}
