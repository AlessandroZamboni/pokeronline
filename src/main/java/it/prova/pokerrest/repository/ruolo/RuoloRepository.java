package it.prova.pokerrest.repository.ruolo;

import it.prova.pokerrest.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface RuoloRepository extends CrudRepository<Authority, Long> {
    Authority findByDescrizioneAndCodice(String descrizione, String codice);

}
