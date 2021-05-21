package it.prova.pokerrest.service.utente;

import it.prova.pokerrest.model.Ruolo;
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
        return null;
    }

    @Override
    public Utente caricaSingoloElemento(Long id) {
        return null;
    }

    @Override
    public Utente caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public Utente aggiorna(Utente utenteInstance) {
        return null;
    }

    @Override
    public Utente inserisciNuovo(Utente utenteInstance) {
        return repository.save(utenteInstance);
    }

    @Override
    public void rimuovi(Utente filmInstance) {

    }

    @Override
    public List<Utente> findByExample(Utente example) {
        return null;
    }

    @Override
    public Utente findByUsername(String user) {
        return repository.findByUsername(user).orElse(null);
    }
}
