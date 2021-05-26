package it.prova.pokerrest.service.ruolo;


import it.prova.pokerrest.model.Ruolo;

import java.util.List;

public interface RuoloService {
    List<Ruolo> listAllElements();

    Ruolo caricaSingoloElemento(Long id);

    Ruolo aggiorna(Ruolo ruoloInstance);

    Ruolo inserisciNuovo(Ruolo ruoloInstance);

    void rimuovi(Ruolo ruoloInstance);

    //  public List<Acquisto> findByExample(Acquisto example);
    public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) ;

    public List<Ruolo> findByArrayIdParam(String[] ids);
}
