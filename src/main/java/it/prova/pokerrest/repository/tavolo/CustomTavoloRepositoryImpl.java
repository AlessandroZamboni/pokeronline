package it.prova.pokerrest.repository.tavolo;

import it.prova.pokerrest.model.Tavolo;
import it.prova.pokerrest.model.Utente;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTavoloRepositoryImpl implements CustomTavoloRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tavolo> findByExample(Tavolo example) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select t from Tavolo t left join fetch t.utenteCreazione u where t.id = t.id ");

        if (StringUtils.isNotEmpty(example.getDenominazione())) {
            whereClauses.add(" t.denominazione like :denominazione ");
            paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
        }
        if (example.getCifraMinima() != null) {
            whereClauses.add(" t.cifraMinima >= :cifraMinima ");
            paramaterMap.put("cifraMinima", example.getCifraMinima());
        }

        if (example.getEsperienzaMin() != null) {
            whereClauses.add(" t.esperienzaMin <= :esperienzaMin ");
            paramaterMap.put("esperienzaMin", example.getEsperienzaMin());
        }

        if (example.getDateCreated() != null) {
            whereClauses.add("u.dateCreated >= :dateCreated ");
            paramaterMap.put("dateCreated", example.getDateCreated());
        }

        if (example.getUtenteCreazione() != null) {
            whereClauses.add(" u = :utenteCreazione ");
            paramaterMap.put("utenteCreazione", example.getUtenteCreazione());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }
        return typedQuery.getResultList();
    }
}
