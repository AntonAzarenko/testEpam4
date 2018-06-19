package com.azarenko.repository;

import com.azarenko.model.Periodical;

import java.math.BigDecimal;
import java.util.List;

public interface PeriodicalReposiroty {
    Periodical save(Periodical entity);

    boolean remove(int id);

    List<Periodical> getAll();

    Periodical get(int id);

    Periodical search(BigDecimal price);

    Periodical search(String title);

    Periodical search(int index);
}
