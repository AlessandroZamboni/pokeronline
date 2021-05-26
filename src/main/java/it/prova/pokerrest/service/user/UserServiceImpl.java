package it.prova.pokerrest.service.user;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.User;
import it.prova.pokerrest.repository.ruolo.RuoloRepository;
import it.prova.pokerrest.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UtenteRepository repository;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public List<User> listAllElements() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public User aggiorna(User utenteInstance) {
        return repository.save(utenteInstance);
    }

    @Override
    public User inserisciNuovo(User utenteInstance) {
        utenteInstance.setDateCreated(new Date());
        return repository.save(utenteInstance);
    }

    @Override
    public void rimuovi(User utenteInstance) {
        repository.delete(utenteInstance);
    }

    @Override
    public List<User> findByExample(User example) {
        return repository.findByExample(example);
    }

    @Override
    public User findByUsername(String user) {
        return repository.findByUsername(user).orElse(null);
    }

    @Override
    public void disabilita(User utenteInstance) {
        utenteInstance.setStato(StatoUtente.DISABILITATO);
        repository.save(utenteInstance);
    }

    @Override
    public void abilita(User utenteInstance) {
        utenteInstance.setStato(StatoUtente.ATTIVO);
        repository.save(utenteInstance);
    }

    @Override
    @Transactional
    public void lasciaPartita(User utenteInstance) {
        utenteInstance.setTavolo(null);
        System.out.println("Esperienza prima: "+utenteInstance.getEsperienzaAccumulata());
        utenteInstance.setEsperienzaAccumulata(utenteInstance.getEsperienzaAccumulata()+1);
        repository.save(utenteInstance);
        System.out.println("Tavolo: "+utenteInstance.getTavolo()+"       Esperienza: "+utenteInstance.getEsperienzaAccumulata());
    }


}
