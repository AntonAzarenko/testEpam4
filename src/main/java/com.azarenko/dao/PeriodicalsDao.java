package com.azarenko.dao;


import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalsDao extends BaseDao<Periodicals> {

    @Override
    List getListEntity() throws DaoException;
}
