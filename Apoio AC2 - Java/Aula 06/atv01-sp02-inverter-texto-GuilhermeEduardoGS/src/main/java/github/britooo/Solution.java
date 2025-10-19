package github.britooo;


public class Solution {
    public static String reverseLetters(String s) {
        String aux = "";

        for (int i = s.length() -1; i >= 0; i--) {
            aux += s.charAt(i);
        }
        return aux;
    }

}
