package it.prova.pokerrest.repository.utente;


import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Utente;

import java.util.List;
import java.util.Optional;

public interface CustomUtenteRepository {
    List<Utente> findByExample(Utente example);
    public Optional<Utente> findOneEager(Long id);
    public Long countByAdmin()  ;
    public List<StatoUtente> listAllStati();



}
