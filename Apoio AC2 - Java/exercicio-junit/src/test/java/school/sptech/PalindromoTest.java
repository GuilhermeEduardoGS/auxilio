package school.sptech;

import org.example.Palindromo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.PalindromoProvider;

class PalindromoTest {

    @Nested
    class MetodoPalindromo {

        @ParameterizedTest
        @ArgumentsSource(PalindromoProvider.class)
        @DisplayName("Deve ignorar diferenças entre maiúsculas e minúsculas")
        void ignorarMaiusculasEMinusculas(String a, Boolean expected) {
            Palindromo palindromo = new Palindromo();
            Boolean resultado = palindromo.isPalindrome(a);
            Assertions.assertEquals(expected, resultado);
        }

        @Test
        @DisplayName("Deve lançar Exception quando o valor for nulo")
        void deveLancarExceptionQuandoNulo() {
            Palindromo palindromo = new Palindromo();

            IllegalArgumentException ex = Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> palindromo.isPalindrome(null)
            );

            Assertions.assertEquals("Texto não pode ser nulo", ex.getMessage());
        }
    }
}
