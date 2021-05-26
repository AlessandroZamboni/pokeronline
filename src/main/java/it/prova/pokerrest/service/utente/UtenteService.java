package it.prova.pokerrest.service.utente;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Utente;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UtenteService {
    List<Utente> listAllElements();

    Utente caricaSingoloElemento(Long id);

    Utente caricaSingoloElementoEager(Long id);

    Utente aggiorna(Utente utenteInstance);

    Utente inserisciNuovo(Utente utenteInstance);

    void rimuovi(Utente filmInstance);

    List<Utente> findByExample(Utente example);

    Utente findByUsername(String user);

    void disabilita(Utente utenteInstance);

    void abilita(Utente utenteInstance);

    @Transactional
    void lasciaPartita(Utente utenteInstance);
}
