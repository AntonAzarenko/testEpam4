package com.azarenko.dao;

import com.azarenko.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    void add(ShoppingCart shoppingCart);
    List<ShoppingCart> getShoppingCartByUserId(int id);
}
