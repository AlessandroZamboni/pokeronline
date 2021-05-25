package it.prova.pokerrest.service.ruolo;

import it.prova.pokerrest.model.Authority;
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
    public List<Authority> listAllElements() {
        return (List<Authority>) repository.findAll();
    }

    @Override
    public Authority caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Authority aggiorna(Authority authorityInstance) {
        return repository.save(authorityInstance);
    }

    @Override
    public Authority inserisciNuovo(Authority authorityInstance) {
        return repository.save(authorityInstance);
    }

    @Override
    public void rimuovi(Authority authorityInstance) {
        repository.delete(authorityInstance);
    }

    @Transactional(readOnly = true)
    public Authority cercaPerDescrizioneECodice(String descrizione, String codice) {
        return repository.findByDescrizioneAndCodice(descrizione, codice);
    }
    @Transactional(readOnly = true)
    public List<Authority> findByArrayIdParam(String[] ids) {
        List<Authority> ruoli = new ArrayList<>();
        if (ids != null && ids.length > 0) {
            for (String ruoloItem : ids) {
                ruoli.add(repository.findById(Long.parseLong(ruoloItem)).orElse(null));
            }
        }
        return ruoli;
    }
}
