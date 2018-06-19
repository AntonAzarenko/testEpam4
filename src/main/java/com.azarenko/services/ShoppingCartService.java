package com.azarenko.services;

import com.azarenko.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCart shoppingCart);

    List<ShoppingCart> getShoppingCartUser(int id);

    BigDecimal getPriceForSubcription(int periodicalId);

    BigDecimal getFullPriceForPayment(int userId);

    void removeShoppingCartUser(int id);
}

