package github.britooo.mocks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrcamentoInvalidoException extends RuntimeException {
    public OrcamentoInvalidoException() {
    }

    public OrcamentoInvalidoException(String message) {
        super(message);
    }
}
