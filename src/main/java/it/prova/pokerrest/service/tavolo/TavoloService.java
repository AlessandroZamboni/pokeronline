package it.prova.pokerrest.service.tavolo;

import it.prova.pokerrest.model.Tavolo;

import java.util.List;

public interface TavoloService {

    List<Tavolo> listAllElements();

    Tavolo caricaSingoloElemento(Long id);

    Tavolo aggiorna(Tavolo tavoloInstance);

    Tavolo inserisciNuovo(Tavolo tavoloInstance);

    void rimuovi(Tavolo tavoloInstance);

    public List<Tavolo> findByExample(Tavolo example);

    Tavolo caricaSingoloAnnuncioEager(Long idAnnuncio);

}
