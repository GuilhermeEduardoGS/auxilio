package github.britooo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("Deve inverter apenas letras simples - caso básico")
    void testCase1() {
        String input = "ab-cd";
        String expected = "dc-ba";
        assertEquals(expected, solution.reverseOnlyLetters(input));
    }

    @Test
    @DisplayName("Deve inverter mantendo símbolos e maiúsculas/minúsculas")
    void testCase2() {
        String input = "a-bC-dEf-ghIj";
        String expected = "j-Ih-gfE-dCba";
        assertEquals(expected, solution.reverseOnlyLetters(input));
    }

    @Test
    @DisplayName("Deve inverter letras preservando números e sinais especiais")
    void testCase3() {
        String input = "Test1ng-Leet=code-Q!";
        String expected = "Qedo1ct-eeLg=ntse-T!";
        assertEquals(expected, solution.reverseOnlyLetters(input));
    }

    @Test
    @DisplayName("Deve retornar string vazia quando entrada é vazia")
    void testEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, solution.reverseOnlyLetters(input));
    }

    @Test
    @DisplayName("Deve retornar a mesma string quando não há letras")
    void testNoLetters() {
        String input = "1234-==--!";
        String expected = "1234-==--!";
        assertEquals(expected, solution.reverseOnlyLetters(input));
    }
}