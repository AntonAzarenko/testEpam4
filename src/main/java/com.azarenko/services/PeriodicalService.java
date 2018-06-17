package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.exceptions.ServiceException;
import com.azarenko.model.Periodical;

import java.util.List;

public interface PeriodicalService {
    List<Periodical> getCatalog() throws ServiceException, DaoException;

    void add(Periodical periodical) throws ServiceException, DaoException;

    void remove(int id) throws ServiceException, DaoException;

    void update(Periodical periodical) throws ServiceException, DaoException;

    Periodical getPeriodical(int id) throws ServiceException, DaoException;

    Periodical search(String param, String value) throws ServiceException, DaoException;

}
