package it.prova.pokerrest.service.ruolo;


import it.prova.pokerrest.model.Authority;

import java.util.List;

public interface RuoloService {
    List<Authority> listAllElements();

    Authority caricaSingoloElemento(Long id);

    Authority aggiorna(Authority authorityInstance);

    Authority inserisciNuovo(Authority authorityInstance);

    void rimuovi(Authority authorityInstance);

    //  public List<Acquisto> findByExample(Acquisto example);
    public Authority cercaPerDescrizioneECodice(String descrizione, String codice) ;

    public List<Authority> findByArrayIdParam(String[] ids);
}
