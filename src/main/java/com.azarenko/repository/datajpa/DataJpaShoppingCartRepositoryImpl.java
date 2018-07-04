package com.azarenko.repository.datajpa;

import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import com.azarenko.repository.datajpa.proxy.ProxyShoppingCartRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DataJpaShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private ProxyShoppingCartRepository proxy;

    @Override

    public void add(ShoppingCart shoppingCart) {
        proxy.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAllByUserID(int id) {
        return proxy.findAllByUserID(id);
    }

    @Override
    @Transactional
    public boolean removeByUserId(int id) {
        return proxy.deleteByUserID(id) != 0;
    }
}
