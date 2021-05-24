package it.prova.pokerrest.service.tavolo;

import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import it.prova.pokerrest.repository.tavolo.TavoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TavoloServiceImpl implements TavoloService {

    @Autowired
    private TavoloRepository repository;

    @Override
    public List<Tavolo> listAllElements() {
        return (List<Tavolo>) repository.findAll();
    }

    @Override
    public Tavolo caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Tavolo caricaSingoloTavoloEager(Long idTavolo) {
        return repository.findSingleTavoloEager(idTavolo);
    }

    @Override
    public List<Tavolo> findByUtenteCreazione(Utente utenteCreazione) {
        return repository.findByUtenteCreazione(utenteCreazione);
    }

    @Override
    public Tavolo aggiorna(Tavolo tavoloInstance) {
        return repository.save(tavoloInstance);
    }

    @Override
    public Tavolo inserisciNuovo(Tavolo tavoloInstance) {
        tavoloInstance.setDateCreated(new Date());
        return repository.save(tavoloInstance);
    }

    @Override
    public void rimuovi(Tavolo tavoloInstance) {
        repository.delete(tavoloInstance);
    }

    @Override
    public List<Tavolo> findByExample(Tavolo example) {
        return repository.findByExample(example);
    }



}
