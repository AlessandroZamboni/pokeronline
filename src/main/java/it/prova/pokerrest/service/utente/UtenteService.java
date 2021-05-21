package it.prova.pokerrest.service.utente;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Utente;

import java.util.List;

public interface UtenteService {
    List<Utente> listAllElements();

    Utente caricaSingoloElemento(Long id);

    Utente caricaSingoloElementoEager(Long id);

    Utente aggiorna(Utente utenteInstance);

    Utente inserisciNuovo(Utente utenteInstance);

    void rimuovi(Utente filmInstance);

    List<Utente> findByExample(Utente example);

}
