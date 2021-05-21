package it.prova.pokerrest.repository.ruolo;

import it.prova.pokerrest.model.Ruolo;
import org.springframework.data.repository.CrudRepository;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
    Ruolo findByDescrizioneAndCodice(String descrizione, String codice);

}
