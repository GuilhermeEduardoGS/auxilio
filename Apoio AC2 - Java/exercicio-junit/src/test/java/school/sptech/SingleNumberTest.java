package school.sptech;

import org.example.SingleNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.SingleNumberProvider;

class SingleNumberTest {

    @Nested
    class MetodoSingleNumber {

        @ParameterizedTest
        @ArgumentsSource(SingleNumberProvider.class)
        @DisplayName("Deve retornar o número único corretamente")
        void deveRetornarNumeroUnico(int[] entrada, int esperado) {
            SingleNumber sn = new SingleNumber();
            int resultado = sn.singleNumber(entrada);

            Assertions.assertEquals(esperado, resultado);
        }
    }
}
