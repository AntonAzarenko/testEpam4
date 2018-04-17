package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao extends DaoBase{
    List<ShoppingCart> getShoppingCartByUserId(int id);
    void removeShoppingCartUser(int id);

    @Override
    AbstractBaseEntity getEntityById(int id);

    @Override
    void add(AbstractBaseEntity entity);
}
