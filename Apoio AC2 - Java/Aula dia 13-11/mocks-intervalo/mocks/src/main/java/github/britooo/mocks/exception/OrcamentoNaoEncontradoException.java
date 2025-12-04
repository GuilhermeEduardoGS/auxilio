package github.britooo.mocks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrcamentoNaoEncontradoException extends RuntimeException {
    public OrcamentoNaoEncontradoException() {
    }

    public OrcamentoNaoEncontradoException(String message) {
        super(message);
    }
}
