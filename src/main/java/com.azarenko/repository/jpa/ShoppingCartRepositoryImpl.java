package com.azarenko.repository.jpa;

import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(ShoppingCart shoppingCart) {
        if(shoppingCart.getId() == null){
            em.persist(shoppingCart);
        }else {
            em.merge(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> getAllByUserID(int id) {
        return null;
    }

    @Override
    public boolean removeByUserId(int id) {
        return false;
    }
}
