import java.util.Arrays;

public class Anagrama {

    public static boolean saoAnagramas(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] a1 = s1.toLowerCase().toCharArray();
        char[] a2 = s2.toLowerCase().toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

}
