package com.azarenko.repository;

import com.azarenko.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ShoppingCartRepository {

    void add(ShoppingCart shoppingCart);

    List<ShoppingCart> getAllByUserID(int id);

    boolean removeByUserId(int id);

    ShoppingCart getByPeriodicalID(int id);

}
