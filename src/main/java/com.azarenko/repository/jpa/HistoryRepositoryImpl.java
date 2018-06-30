package com.azarenko.repository.jpa;

import com.azarenko.model.HistoryPrice;
import com.azarenko.repository.HistoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class HistoryRepositoryImpl implements HistoryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<HistoryPrice> getAllByPeriodicalId(int id) {
        return em.createNamedQuery(HistoryPrice.PERIODICAL_ID, HistoryPrice.class)
                .setParameter("idPeriodical", id).getResultList();
    }

    @Override
    public List<HistoryPrice> searchAllByName(String name) {
        return em.createNamedQuery(HistoryPrice.NAME, HistoryPrice.class)
                .setParameter("namePeriodicals", name).getResultList();
    }

    @Override
    @Transactional
    public void save(HistoryPrice historyPrice) {
        if (historyPrice.getId() == null) {
            em.persist(historyPrice);
        } else {
            em.merge(historyPrice);
        }
    }

    @Override
    public List<HistoryPrice> getAll() {
        return em.createNamedQuery(HistoryPrice.AlL_SORTED, HistoryPrice.class).getResultList();
    }
}
