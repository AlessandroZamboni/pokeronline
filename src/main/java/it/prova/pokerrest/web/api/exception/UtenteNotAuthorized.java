package it.prova.pokerrest.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UtenteNotAuthorized extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UtenteNotAuthorized(String message) {
        super(message);
    }
}
