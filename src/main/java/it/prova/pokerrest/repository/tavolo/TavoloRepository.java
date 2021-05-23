package it.prova.pokerrest.repository.tavolo;

import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TavoloRepository extends CrudRepository<Tavolo, Long>, CustomTavoloRepository {
    @Query("from Tavolo t left join fetch t.giocatori g where t.id = ?1")
    Tavolo findSingleTavoloEager(Long id);

    List<Tavolo> findByUtenteCreazione(Utente utenteCreazione);

}
