package com.azarenko.services;


import com.azarenko.model.ShoppingCart;

import java.math.BigDecimal;

import java.util.List;


public class ShoppingCartServiceImpl implements ShoppingCartService {


    @Override
    public void add(ShoppingCart shoppingCart) {

    }

    @Override
    public List<ShoppingCart> getShoppingCartUser(int id) {
        return null;
    }

    @Override
    public BigDecimal getPriceForSubcription(int periodicalId) {
        return null;
    }

    @Override
    public BigDecimal getFullPriceForPayment(int userId) {
        return null;
    }

    @Override
    public void removeShoppingCartUser(int id) {

    }
}
