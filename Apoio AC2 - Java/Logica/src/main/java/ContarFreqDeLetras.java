public class ContarFreqDeLetras {

    public static void contarLetras(String s) {
        int[] freq = new int[26];
        for (char c : s.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') freq[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) System.out.println((char)(i + 'a') + ": " + freq[i]);
        }
    }

}
