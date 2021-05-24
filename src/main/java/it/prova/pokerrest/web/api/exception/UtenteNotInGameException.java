package it.prova.pokerrest.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UtenteNotInGameException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UtenteNotInGameException(String message) {
        super(message);
    }
}
