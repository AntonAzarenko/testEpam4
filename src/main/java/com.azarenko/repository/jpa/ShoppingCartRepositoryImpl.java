package com.azarenko.repository.jpa;

import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional(readOnly = true)
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(ShoppingCart shoppingCart) {
        if(shoppingCart.getId() == null){
            em.persist(shoppingCart);
        }else {
            em.merge(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> getAllByUserID(int id) {
        return em.createNamedQuery(ShoppingCart.ALL_SORTED, ShoppingCart.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public boolean removeByUserId(int id) {
        return em.createNamedQuery(ShoppingCart.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }
}
