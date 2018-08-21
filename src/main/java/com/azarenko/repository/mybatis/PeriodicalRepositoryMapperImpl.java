package com.azarenko.repository.mybatis;

import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public class PeriodicalRepositoryMapperImpl implements PeriodicalReposiroty {
    @Override
    public Periodical save(Periodical entity) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public List<Periodical> getAll() {
        return null;
    }

    @Override
    public Periodical get(int id) {
        return null;
    }

    @Override
    public Periodical search(BigDecimal price) {
        return null;
    }

    @Override
    public Periodical search(String title) {
        return null;
    }

    @Override
    public Periodical search(int index) {
        return null;
    }
}
