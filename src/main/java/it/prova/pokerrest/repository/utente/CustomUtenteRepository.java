package it.prova.pokerrest.repository.utente;


import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.User;
import it.prova.pokerrest.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomUtenteRepository {
    List<User> findByExample(User example);
    public Optional<User> findOneEager(Long id);
    public Long countByAdmin()  ;
    public List<StatoUtente> listAllStati();



}
