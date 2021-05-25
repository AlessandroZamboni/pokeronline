package it.prova.pokerrest.repository.utente;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<User, Long>, CustomUtenteRepository {
    Optional<User> findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    //caricamento eager, ovviamente si può fare anche con jpql
    @EntityGraph(attributePaths = { "ruoli" })
    User findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
}
