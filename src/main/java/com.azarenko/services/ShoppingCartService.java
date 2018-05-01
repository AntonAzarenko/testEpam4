package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCart shoppingCart) throws DaoException;

    void goToPay();

    List<ShoppingCart> getShoppingCartUser(int id) throws DaoException;

    BigDecimal getPriceForSubcription(int  periodicalId) throws DaoException;

    BigDecimal getFullPriceForPayment(int userId) throws DaoException;

    void removeShoppingCartUser(int id) throws DaoException;
}
