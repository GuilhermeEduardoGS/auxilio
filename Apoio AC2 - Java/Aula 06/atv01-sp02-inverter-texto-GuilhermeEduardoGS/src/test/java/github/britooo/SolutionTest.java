package github.britooo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @Test
    @DisplayName("Deve inverter string simples")
    void testReverseSimple() {
        assertEquals("cba", Solution.reverseLetters("abc"));
    }

    @Test
    @DisplayName("Deve inverter string com números")
    void testReverseWithNumbers() {
        assertEquals("dc1ba", Solution.reverseLetters("ab1cd"));
    }

    @Test
    @DisplayName("Deve inverter string com símbolos")
    void testReverseWithSymbols() {
        assertEquals("jIhg-fEd-Cb-a", Solution.reverseLetters("a-bC-dEf-ghIj"));
    }

    @Test
    @DisplayName("Deve inverter string vazia (continua vazia)")
    void testReverseEmpty() {
        assertEquals("", Solution.reverseLetters(""));
    }

    @Test
    @DisplayName("Deve inverter string misturando letras, números e símbolos")
    void testReverseMixed() {
        assertEquals("!Q-edoc=teeL-gn1tseT", Solution.reverseLetters("Test1ng-Leet=code-Q!"));
    }
}