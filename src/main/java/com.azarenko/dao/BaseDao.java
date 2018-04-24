package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;

import java.util.List;

public interface BaseDao<Type extends AbstractBaseEntity>{
    Type getEntityById(int id);
    void add(Type entity) throws DaoException;
    void remove (int id);
    void update (Type entity);
    List<Type> getListEntity() throws DaoException;
}
