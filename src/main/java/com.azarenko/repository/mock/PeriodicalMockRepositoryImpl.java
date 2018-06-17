package com.azarenko.repository.mock;

import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class PeriodicalMockRepositoryImpl implements PeriodicalReposiroty {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalMockRepositoryImpl.class);

    private Map<Integer, Map<Integer, Periodical>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    {
        add(new Periodical(1, "Вяселка", "Развлекательный журнал", 2, new BigDecimal(Double.valueOf(2.25))), 2);
        add(new Periodical(2, "СпидИнфо", "Развлекательный журнал", 2, new BigDecimal(Double.valueOf(2.25))), 2);
        add(new Periodical(3, "Вечерний Витебск", "Развлекательный журнал", 2, new BigDecimal(Double.valueOf(2.26))), 2);
        add(new Periodical(4, "Секретные исследования", "Развлекательный журнал", 2, new BigDecimal(Double.valueOf(2.25))), 2);
        add(new Periodical(5, "Комсамолка", "Развлекательный журнал", 2, new BigDecimal(Double.valueOf(2.25))), 2);
    }

    @Override
    public Periodical add(Periodical periodical, int userId) {
        Objects.requireNonNull(periodical);

        Integer periodicalId = periodical.getId();

        if (periodical.isNew()) {
            periodicalId = counter.incrementAndGet();
        } else if (get(periodicalId, userId) == null) {
            return null;
        }
        Map<Integer, Periodical> periodicals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        periodicals.put(periodicalId, periodical);
        repository.put(userId,periodicals);
        return periodical;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Periodical entity) {

    }

    @Override
    public Collection<Periodical> getListEntity() {
        return (Collection<Periodical>) repository;
    }

    @Override
    public Periodical get(int id, int userId) {
        return null;
    }

    @Override
    public Periodical get(int id) {
        return null;
    }
}
