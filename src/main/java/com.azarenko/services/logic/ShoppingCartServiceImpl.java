package com.azarenko.services.logic;


import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionTimeUtil;
import com.azarenko.to.ShoppingCartTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private SubscriptionTimeUtil subscriptionTimeUtil;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PeriodicalService service;

    @Override
    @Transactional
    public void add(ShoppingCart shoppingCart) {
        if(getByPeriodicalID(shoppingCart.getPeriodicalId())){
            return;
        }
        shoppingCartRepository.add(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAllByUserID(int id) {
        return shoppingCartRepository.getAllByUserID(id);
    }

    @Override
    public BigDecimal getPriceForSubcription(Periodical periodical) {
        int days = subscriptionTimeUtil.getNumberOfExist(periodical);
        BigDecimal temp = periodical.getPrice().multiply(new BigDecimal(days));
        return periodical.getPrice().multiply(new BigDecimal(days));
    }

    @Override
    public BigDecimal getFullPriceForPayment(int userId) {
        BigDecimal fullPrice = new BigDecimal(1);
        List<ShoppingCart> list = getAllByUserID(userId);
        Map<Integer, Periodical> map = getMapPeriodicals();
        if (list != null) {
            for (ShoppingCart cart : list) {
                if (map.containsKey(cart.getPeriodicalId())) {
                    BigDecimal temp = getPriceForSubcription(map.get(cart.getPeriodicalId()));
                    fullPrice = fullPrice.add(temp);
                }
            }
        }
        return fullPrice;
    }

    @Override
    @Transactional
    public void removeShoppingCartUser(int id) {
        shoppingCartRepository.removeByUserId(id);
    }

    @Override
    public boolean getByPeriodicalID(int id) {
        return shoppingCartRepository.getByPeriodicalID(id) != null;
    }

    @Override
    public ShoppingCartTo get(int id) {
       //todo
        return null;
    }


    public Map<Integer, Periodical> getMapPeriodicals() {
        List<Periodical> allPeriodicals = service.getAll();
        Map<Integer, Periodical> map = new HashMap<>();
        for (Periodical per : allPeriodicals) {
            map.put(per.getId(), per);
        }
        return map;
    }


}
