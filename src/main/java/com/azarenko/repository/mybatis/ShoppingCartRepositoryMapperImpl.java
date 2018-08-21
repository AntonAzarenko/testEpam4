package com.azarenko.repository.mybatis;

import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import com.azarenko.services.ShoppingCartService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartRepositoryMapperImpl implements ShoppingCartRepository {
    @Override
    public void add(ShoppingCart shoppingCart) {

    }

    @Override
    public List<ShoppingCart> getAllByUserID(int id) {
        return null;
    }

    @Override
    public boolean removeByUserId(int id) {
        return false;
    }

    @Override
    public ShoppingCart getByPeriodicalID(int id) {
        return null;
    }
}
