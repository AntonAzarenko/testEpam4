package com.azarenko.services;

import com.azarenko.dao.ShoppingCartDao;
import com.azarenko.dao.ShoppingCartDaoImpl;
import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartServiceImpl() {
        shoppingCartDao = new ShoppingCartDaoImpl();
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void goToPay() {

    }

    @Override
    public List<ShoppingCart> getShoppingCartUser(int id) {
        return shoppingCartDao.getShoppingCartByUserId(id);

    }


}
