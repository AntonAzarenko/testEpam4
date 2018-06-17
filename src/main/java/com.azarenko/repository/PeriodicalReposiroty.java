package com.azarenko.repository;

import com.azarenko.exceptions.RepositoryExceptions;
import com.azarenko.model.Periodical;

import java.util.Collection;
import java.util.List;

public interface PeriodicalReposiroty {
    Periodical add(Periodical entity, int userId) ;

    void remove(int id) ;

    void update(Periodical entity);

    Collection<Periodical> getListEntity() ;

    Periodical get(int id, int userId);

    Periodical get(int id);
}
