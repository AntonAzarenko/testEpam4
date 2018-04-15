package com.azarenko.services;

import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;

import java.util.List;

public interface ShoppingCartService {
    void add (ShoppingCart shoppingCart);
    void goToPay();
    List<ShoppingCart> getShoppingCartUser(int id);
}
