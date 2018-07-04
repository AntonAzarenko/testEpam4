package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProxyShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findAllByUserID(int id);

    @Override
    ShoppingCart save(ShoppingCart cart);


    int deleteByUserID(Integer integer);
}
