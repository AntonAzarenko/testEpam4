package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCart shoppingCart) throws DaoException;

    List<ShoppingCart> getShoppingCartUser(int id) throws DaoException;

    BigDecimal getPriceForSubcription(int periodicalId) throws DaoException;

    BigDecimal getFullPriceForPayment(int userId) throws DaoException;

    void removeShoppingCartUser(int id) throws DaoException;
}
