package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalService {
    List<Periodicals> getCatalog() throws ServiceException, DaoException;

    void add(Periodicals periodicals) throws ServiceException, DaoException;

    void remove(int id) throws ServiceException, DaoException;

    void update(Periodicals periodicals) throws ServiceException, DaoException;

    Periodicals getPeriodical(int id) throws ServiceException, DaoException;

    Periodicals search(String param, String value) throws ServiceException, DaoException;

}
