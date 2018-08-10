package com.azarenko.services;

import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.to.ShoppingCartShowTo;
import com.azarenko.to.ShoppingCartTo;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCart shoppingCart);

    List<ShoppingCart> getAllByUserID(int id);

    BigDecimal getPriceForSubcription(Periodical periodical);

    BigDecimal getFullPriceForPayment(int userId);

    void removeShoppingCartUser(int id);

    boolean getByPeriodicalID(int id);

    ShoppingCart checkAndCreate(ShoppingCartTo cartTo);

    List<ShoppingCartShowTo> getAllTO(int id);

    BigDecimal getCost(List<ShoppingCartShowTo> list);
}

