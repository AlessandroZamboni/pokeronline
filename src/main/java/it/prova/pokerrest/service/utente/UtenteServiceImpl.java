package it.prova.pokerrest.service.utente;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.repository.ruolo.RuoloRepository;
import it.prova.pokerrest.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository repository;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public List<Utente> listAllElements() {
        return (List<Utente>) repository.findAll();
    }

    @Override
    public Utente caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Utente caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public Utente aggiorna(Utente utenteInstance) {
        return repository.save(utenteInstance);
    }

    @Override
    public Utente inserisciNuovo(Utente utenteInstance) {
        utenteInstance.setDateCreated(new Date());
        return repository.save(utenteInstance);
    }

    @Override
    public void rimuovi(Utente utenteInstance) {
        repository.delete(utenteInstance);
    }

    @Override
    public List<Utente> findByExample(Utente example) {
        return repository.findByExample(example);
    }

    @Override
    public Utente findByUsername(String user) {
        return repository.findByUsername(user).orElse(null);
    }

    @Override
    public void disabilita(Utente utenteInstance) {
        utenteInstance.setStato(StatoUtente.DISABILITATO);
        repository.save(utenteInstance);
    }

    @Override
    public void abilita(Utente utenteInstance) {
        utenteInstance.setStato(StatoUtente.ATTIVO);
        repository.save(utenteInstance);
    }

    @Override
    @Transactional
    public void lasciaPartita(Utente utenteInstance) {
        utenteInstance.setTavolo(null);
        System.out.println("Esperienza prima: "+utenteInstance.getEsperienzaAccumulata());
        utenteInstance.setEsperienzaAccumulata(utenteInstance.getEsperienzaAccumulata()+1);
        repository.save(utenteInstance);
        System.out.println("Tavolo: "+utenteInstance.getTavolo()+"       Esperienza: "+utenteInstance.getEsperienzaAccumulata());
    }


}
