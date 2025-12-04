package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularInssProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(1517.00, 1600.00, 2500.00, 3500.00)
                .map(salario -> {
                    double esperado;
                    if (salario < 1518.00) {
                        esperado = -1;
                    } else if (salario <= 2000.00) {
                        esperado = salario * 0.10;
                    } else if (salario <= 3000.00) {
                        esperado = salario * 0.15;
                    } else {
                        esperado = salario * 0.20;
                    }
                    return Arguments.of(salario, esperado);
                });
    }

}
