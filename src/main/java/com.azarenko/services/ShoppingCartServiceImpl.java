package com.azarenko.services;

import com.azarenko.dao.ShoppingCartDao;
import com.azarenko.dao.ShoppingCartDaoImpl;
import com.azarenko.model.ShoppingCart;
import org.apache.log4j.Logger;

import java.util.List;


public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final static Logger log = Logger.getLogger(ShoppingCartServiceImpl.class);
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
