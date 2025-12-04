package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PalindromoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("arara", true),
                Arguments.of("AnA", true),
                Arguments.of("Teste", false),
                Arguments.of("socorram me subi no onibus em marrocos", true)
        );
    }
}
