package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalService {
    List<Periodicals> getCatalog() throws ServiceException, DaoException;

    void add(Periodicals periodicals) throws DaoException;

    void remove(int id);

    void update(Periodicals periodicals);

    Periodicals getPeriodical(int id);

}
