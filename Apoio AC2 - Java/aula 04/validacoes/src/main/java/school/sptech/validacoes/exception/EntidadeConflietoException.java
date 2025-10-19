package school.sptech.validacoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeConflietoException extends RuntimeException {
    public EntidadeConflietoException(String message) {
        super(message);
    }
}
