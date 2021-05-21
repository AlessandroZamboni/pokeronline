package it.prova.pokerrest.repository.tavolo;

import it.prova.pokerrest.model.Tavolo;

import java.util.List;

public interface CustomTavoloRepository {
    public List<Tavolo> findByExample(Tavolo example);
}
