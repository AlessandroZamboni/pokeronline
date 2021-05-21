package it.prova.pokerrest.service.ruolo;


import it.prova.pokerrest.model.Ruolo;

import java.util.List;

public interface RuoloService {
    public List<Ruolo> listAll();

    public Ruolo caricaSingoloElemento(Long id);


    public void aggiorna(Ruolo ruoloInstance);

    public void inserisciNuovo(Ruolo ruoloInstance);

    public void rimuovi(Ruolo ruoloInstance);

    //  public List<Acquisto> findByExample(Acquisto example);
    public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) ;

    public List<Ruolo> findByArrayIdParam(String[] ids);
}
