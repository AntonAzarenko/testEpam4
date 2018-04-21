package com.azarenko.dao;


import com.azarenko.model.AbstractBaseEntity;

import java.util.List;

public interface PeriodicalsDao extends BaseDao {
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
