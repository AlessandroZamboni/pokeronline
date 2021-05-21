package it.prova.pokerrest.service.tavolo;

import it.prova.pokerrest.model.Tavolo;

import java.util.List;

public interface TavoloService {

    public List<Tavolo> listAllElements();

    public Tavolo caricaSingoloElemento(Long id);

    Tavolo caricaSingoloElementoEager(Long id);

    public void aggiorna(Tavolo annuncioInstance);

    public void inserisciNuovo(Tavolo annuncioInstance);

    public void rimuovi(Tavolo annuncioInstance);

    public List<Tavolo> findByExample(Tavolo example);

    Tavolo caricaSingoloAnnuncioEager(Long idAnnuncio);

}
