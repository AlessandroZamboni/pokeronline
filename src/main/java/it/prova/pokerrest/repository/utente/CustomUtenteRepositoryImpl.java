package it.prova.pokerrest.repository.utente;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.Utente;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

public class CustomUtenteRepositoryImpl implements CustomUtenteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Utente> findByExample(Utente example) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select u from Utente u left join fetch u.ruoli r " +
                "left join fetch u.tavolo t where u.id = u.id ");

        if (StringUtils.isNotEmpty(example.getNome())) {
            whereClauses.add(" u.nome like :nome ");
            paramaterMap.put("nome", "%" + example.getNome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getCognome())) {
            whereClauses.add(" u.cognome like :cognome ");
            paramaterMap.put("cognome", "%" + example.getCognome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getUsername())) {
            whereClauses.add(" u.username like :username ");
            paramaterMap.put("username", "%" + example.getUsername() + "%");
        }

        if (example.getEsperienzaAccumulata() != null) {
            whereClauses.add("u.esperienzaAccumulata >= :esperienzaAccumulata ");
            paramaterMap.put("esperienzaAccumulata", example.getEsperienzaAccumulata());
        }

        if (example.getCreditoResiduo() != null) {
            whereClauses.add("u.creditoResiduo >= :creditoResiduo ");
            paramaterMap.put("creditoResiduo", example.getCreditoResiduo());
        }

        if (example.getTavolo() != null) {
            whereClauses.add("t = :tavolo ");
            paramaterMap.put("tavolo", example.getTavolo());
        }

        if (example.getDateCreated() != null) {
            whereClauses.add("u.dateCreated >= :dateCreated ");
            paramaterMap.put("dateCreated", example.getDateCreated());
        }
        if (example.getStato() != null) {
            whereClauses.add("u.stato = :stato ");
            paramaterMap.put("stato", example.getStato());
        }
        if (example.getRuoli() != null && !example.getRuoli().isEmpty()) {
            whereClauses.add(" r in :ruoli ");
            paramaterMap.put("ruoli", example.getRuoli());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Utente> findOneEager(Long id) {
        TypedQuery<Utente> query = entityManager
                .createQuery("select u FROM Utente u left join fetch u.ruoli r where u.id = :idUtente ", Utente.class);
        query.setParameter("idUtente", id);
        return query.getResultStream().findFirst();
    }

    @Override
    public Long countByAdmin() {
        TypedQuery<Long> query = entityManager.createQuery("select count (u.id) FROM Utente u join u.ruoli r where r.id = 1 and u.stato = 'ATTIVO'",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<StatoUtente> listAllStati() {
        List<StatoUtente> stati = new ArrayList<>();
        Collections.addAll(stati, StatoUtente.values());
        return stati;
    }
}
