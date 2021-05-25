package it.prova.pokerrest.repository.utente;

import it.prova.pokerrest.model.StatoUtente;
import it.prova.pokerrest.model.User;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

public class CustomUtenteRepositoryImpl implements CustomUtenteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findByExample(User example) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select u from Utente u left join fetch u.authorities a " +
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
        if (example.getAuthorities() != null && !example.getAuthorities().isEmpty()) {
            whereClauses.add(" a in :authorities ");
            paramaterMap.put("authorities", example.getAuthorities());
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<User> typedQuery = entityManager.createQuery(queryBuilder.toString(), User.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }
        return typedQuery.getResultList();
    }

    @Override
    public Optional<User> findOneEager(Long id) {
        TypedQuery<User> query = entityManager
                .createQuery("select u FROM User u left join fetch u.authorities r where u.id = :idUtente ", User.class);
        query.setParameter("idUtente", id);
        return query.getResultStream().findFirst();
    }

    @Override
    public Long countByAdmin() {
        TypedQuery<Long> query = entityManager.createQuery("select count (u.id) FROM User u join u.authorities r where r.id = 1 and u.stato = 'ATTIVO'",
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
