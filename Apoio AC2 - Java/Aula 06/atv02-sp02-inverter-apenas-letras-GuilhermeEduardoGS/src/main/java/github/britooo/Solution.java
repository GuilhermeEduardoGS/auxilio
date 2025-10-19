package github.britooo;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static String reverseOnlyLetters(String s) {
        String aux = "";
        String palavra = "";
        Integer cont = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            if (Character.isLetter(s.charAt(i))){
                aux += s.charAt(i);
            }
        }

        for (int j = 0; j < s.length(); j++) {
            if (Character.isLetter(s.charAt(j))){
                palavra += aux.charAt(cont);
                cont++;
            }
            else {
                palavra += s.charAt(j);
            }
        }

        return palavra;
    }
}
