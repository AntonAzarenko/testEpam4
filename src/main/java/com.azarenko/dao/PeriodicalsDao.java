package com.azarenko.dao;


import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Periodicals;

import java.util.List;

public interface PeriodicalsDao extends DaoBase {
    @Override
    AbstractBaseEntity getEntityById(int id);

    @Override
    void add(AbstractBaseEntity entity);

    @Override
    void remove(int id);

    @Override
    void update(AbstractBaseEntity entity);

    @Override
    List getListEntity();
}
