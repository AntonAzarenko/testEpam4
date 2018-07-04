package com.azarenko.repository.datajpa;

import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;
import com.azarenko.repository.datajpa.proxy.ProxyPeriodicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class DataJpaPeriodicalRepositoryImpl implements PeriodicalReposiroty {
    @Autowired
    private ProxyPeriodicalRepository proxy;


    @Override
    @Transactional
    public Periodical save(Periodical entity) {
        return proxy.save(entity);
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public List<Periodical> getAll() {
        return proxy.findAll();
    }

    @Override
    public Periodical get(int id) {
        return proxy.getById(id);
    }

    @Override
    public Periodical search(BigDecimal price) {
        return proxy.getByPrice(price);
    }

    @Override
    public Periodical search(String title) {
        return proxy.getByTitle(title);
    }

    @Override
    public Periodical search(int index) {
        return proxy.getByIndex(index);
    }
}
