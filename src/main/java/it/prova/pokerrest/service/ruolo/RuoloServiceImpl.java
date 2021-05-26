package it.prova.pokerrest.service.ruolo;

import it.prova.pokerrest.model.Ruolo;
import it.prova.pokerrest.repository.ruolo.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuoloServiceImpl implements RuoloService {

    @Autowired
    private RuoloRepository repository;


    @Override
    public List<Ruolo> listAllElements() {
        return (List<Ruolo>) repository.findAll();
    }

    @Override
    public Ruolo caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Ruolo aggiorna(Ruolo ruoloInstance) {
        return repository.save(ruoloInstance);
    }

    @Override
    public Ruolo inserisciNuovo(Ruolo ruoloInstance) {
        return repository.save(ruoloInstance);
    }

    @Override
    public void rimuovi(Ruolo ruoloInstance) {
        repository.delete(ruoloInstance);
    }

    @Transactional(readOnly = true)
    public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
        return repository.findByDescrizioneAndCodice(descrizione, codice);
    }
    @Transactional(readOnly = true)
    public List<Ruolo> findByArrayIdParam(String[] ids) {
        List<Ruolo> ruoli = new ArrayList<>();
        if (ids != null && ids.length > 0) {
            for (String ruoloItem : ids) {
                ruoli.add(repository.findById(Long.parseLong(ruoloItem)).orElse(null));
            }
        }
        return ruoli;
    }
}
