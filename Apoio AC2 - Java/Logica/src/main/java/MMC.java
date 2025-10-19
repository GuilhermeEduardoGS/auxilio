public class MMC {
// Minumo multiplicador comum
    public static int mmc(int a, int b) {
        int maior = Math.max(a, b);
        int mmc = maior;

        while (true) {
            if (mmc % a == 0 && mmc % b == 0) {
                return mmc;
            }
            mmc++;
        }
    }

}
