package com.azarenko.dao;


import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Periodicals;

import java.math.BigDecimal;
import java.util.List;

public interface PeriodicalsDao extends BaseDao<Periodicals> {

    @Override
    List getListEntity() throws DaoException;

    Periodicals search(String value) throws DaoException;

    Periodicals search(int value) throws DaoException;

    Periodicals search(BigDecimal value) throws DaoException;
}
