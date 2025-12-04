package school.sptech;

import org.example.CalcularInss;
import org.example.SalarioInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.api.Test;
import school.sptech.provider.CalcularInssProvider;

class CalcularInssTest {

    @Nested
    class MetodoCalcularInss {

        @ParameterizedTest
        @ArgumentsSource(CalcularInssProvider.class)
        @DisplayName("Deve calcular corretamente o valor do INSS conforme a faixa salarial")
        void deveCalcularCorretamente(double salario, double esperado) {
            CalcularInss service = new CalcularInss();
            double resultado = service.calcularInss(salario);
            Assertions.assertEquals(esperado, resultado, 0.01);
        }

        @Test
        @DisplayName("Deve lançar exceção quando o salário for menor que o mínimo")
        void deveLancarExceptionQuandoSalarioMenorQueMinimo() {
            CalcularInss service = new CalcularInss();

            SalarioInvalidoException ex = Assertions.assertThrows(
                    SalarioInvalidoException.class,
                    () -> service.calcularInss(1518.00)
            );

            Assertions.assertEquals("Salário não pode ser menor que o salário mínimo", ex.getMessage());
        }
    }
}
