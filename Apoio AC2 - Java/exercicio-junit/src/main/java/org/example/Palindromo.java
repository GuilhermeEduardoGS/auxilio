package org.example;

public class Palindromo {

    public boolean isPalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Texto não pode ser nulo");
        }

        String removerEspacos = text.replace(" ", "");

        String invertida = new StringBuilder(removerEspacos).reverse().toString();

        return removerEspacos.equalsIgnoreCase(invertida.trim());
    }
}
