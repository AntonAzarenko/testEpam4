package com.azarenko.dao;


import com.azarenko.model.Periodical;

import java.math.BigDecimal;
import java.util.List;

public interface PeriodicalsDao extends BaseDao<Periodical> {

    @Override
    List getListEntity() throws DaoException;

    Periodical search(String value) throws DaoException;

    Periodical search(int value) throws DaoException;

    Periodical search(BigDecimal value) throws DaoException;
}
