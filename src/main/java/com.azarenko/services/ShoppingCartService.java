package com.azarenko.services;

import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCart shoppingCart);

    void goToPay();

    List<ShoppingCart> getShoppingCartUser(int id);

    BigDecimal getPriceForSubcription(int id, Date start, Date end);

    BigDecimal getFullPriceForPayment(int userId);

    void removeShoppingCartUser(int id);
}
