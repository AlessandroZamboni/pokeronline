package it.prova.pokerrest.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UtenteIsNotAdminException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UtenteIsNotAdminException(String message) {
        super(message);
    }
}
