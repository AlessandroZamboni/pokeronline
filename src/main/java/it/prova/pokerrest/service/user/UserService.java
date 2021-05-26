package it.prova.pokerrest.service.user;

import it.prova.pokerrest.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> listAllElements();

    User caricaSingoloElemento(Long id);

    User caricaSingoloElementoEager(Long id);

    User aggiorna(User utenteInstance);

    User inserisciNuovo(User utenteInstance);

    void rimuovi(User filmInstance);

    List<User> findByExample(User example);

    User findByUsername(String user);

    void disabilita(User utenteInstance);

    void abilita(User utenteInstance);

    @Transactional
    void lasciaPartita(User utenteInstance);
}
