package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;

import java.util.List;

public interface BaseDao<Type extends AbstractBaseEntity> {
    Type getEntityById(int id) throws DaoException;

    void add(Type entity) throws DaoException;

    void remove(int id) throws DaoException;

    void update(Type entity) throws DaoException;

    List<Type> getListEntity() throws DaoException;
}
