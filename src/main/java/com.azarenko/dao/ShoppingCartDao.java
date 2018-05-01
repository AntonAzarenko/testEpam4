package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao extends BaseDao<ShoppingCart> {
    List<ShoppingCart> getShoppingCartByUserId(int id) throws DaoException;
    void removeShoppingCartUser(int id) throws DaoException;

    @Override
    ShoppingCart getEntityById(int id);

    @Override
    void add(ShoppingCart entity) throws DaoException;
}
