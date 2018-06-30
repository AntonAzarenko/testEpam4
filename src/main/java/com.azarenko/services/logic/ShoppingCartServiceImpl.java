package com.azarenko.services.logic;


import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private SubscriptionTimeUtil subscriptionTimeUtil;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PeriodicalService service;

    @Override
    public void add(ShoppingCart shoppingCart) {
        shoppingCartRepository.add(shoppingCart);
    }

    @Override
    @Transactional
    public List<ShoppingCart> getAllByUserID(int id) {
        return shoppingCartRepository.getAllByUserID(id);
    }

    @Override
    public BigDecimal getPriceForSubcription(Periodical periodical) {
        int days = subscriptionTimeUtil.getNumberOfExist(periodical);
        return periodical.getPrice().multiply(new BigDecimal(days));
    }

    @Override
    @Transactional
    public BigDecimal getFullPriceForPayment(int userId) {
        BigDecimal fullPrice = new BigDecimal(0);
        List<ShoppingCart> list = getAllByUserID(userId);
        Map<Integer, Periodical> map = getMapPeriodicals();
        for (ShoppingCart cart : list) {
            if (map.containsKey(cart.getId())) {
                fullPrice.add(getPriceForSubcription(map.get(cart.getId())));
            }
        }
        return fullPrice;
    }

    @Override
    @Transactional
    public void removeShoppingCartUser(int id) {
        shoppingCartRepository.removeByUserId(id);
    }

    @Transactional
    public Map<Integer, Periodical> getMapPeriodicals() {
        List<Periodical> allPeriodicals = service.getAll();
        Map<Integer, Periodical> map = new HashMap<>();
        for (Periodical per : allPeriodicals) {
            map.put(per.getId(), per);
        }
        return map;
    }
}
