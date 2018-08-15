package com.azarenko.repository.jpa;

import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;


@Transactional(readOnly = true)
public class PeriodicalRepositoryImpl implements PeriodicalReposiroty {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Periodical save(Periodical periodical) {
        if (periodical.isNew()) {
            em.persist(periodical);
        } else {
            em.merge(periodical);
        }
        return periodical;
    }

    @Override
    @Transactional
    public boolean remove(int id) {
        return em.createNamedQuery(Periodical.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public List<Periodical> getAll() {
        return em.createNamedQuery(Periodical.All_SORTED, Periodical.class).getResultList();
    }


    @Override
    public Periodical get(int id) {
        return em.find(Periodical.class, id);
    }

    @Override
    public Periodical search(BigDecimal price) {
        return em.createNamedQuery(Periodical.PRICE,Periodical.class)
                .setParameter("price", price).getSingleResult();
    }

    @Override
    public Periodical search(String title) {
        return em.createNamedQuery(Periodical.TITLE,Periodical.class)
                .setParameter("title", title).getSingleResult();
    }

    @Override
    public Periodical search(int index) {
        return em.createNamedQuery(Periodical.INDEX, Periodical.class)
                .setParameter("index", index).getSingleResult();
    }
}
