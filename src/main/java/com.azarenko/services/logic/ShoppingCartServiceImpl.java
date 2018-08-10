package com.azarenko.services.logic;


import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.repository.ShoppingCartRepository;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionTimeUtil;
import com.azarenko.to.ShoppingCartShowTo;
import com.azarenko.to.ShoppingCartTo;
import com.azarenko.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private SubscriptionTimeUtil subscriptionTimeUtil;

    @Autowired
    ShoppingCartShowTo cartShowTo;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PeriodicalService service;

    @Override
    @Transactional
    public void add(ShoppingCart shoppingCart) {
        if (getByPeriodicalID(shoppingCart.getPeriodicalId())) {
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
    public ShoppingCart checkAndCreate(ShoppingCartTo cartTo) {
        //todo
        ShoppingCart cart = new ShoppingCart();
        int timeSubscription = getTimeSubscription(cartTo);
        if (timeSubscription < 3) {
            if (cartTo.isFirstHalfYear() && (subscriptionTimeUtil.getHalfYear() != 0)) {
                cart.setUserID(1);
                cart.setPeriodicalId(cartTo.getPeriodicalId());
                cart.setStart(subscriptionTimeUtil.getStartDate(timeSubscription));
                cart.setTime(timeSubscription);
                cart.setCountPer(subscriptionTimeUtil.getNumberOfExist(service.get(cartTo.getPeriodicalId())));
                cart.setPrice(getPriceForSubcription(service.get(cartTo.getPeriodicalId())));
            } else {
                cart.setUserID(1);
                cart.setPeriodicalId(cartTo.getPeriodicalId());
                cart.setStart(subscriptionTimeUtil.getStartDate(timeSubscription));
                cart.setEnd(subscriptionTimeUtil.getEndDate(timeSubscription));
                cart.setTime(timeSubscription);
                cart.setCountPer(subscriptionTimeUtil.getNumberOfExist(service.get(cartTo.getPeriodicalId())));
                cart.setPrice(getPriceForSubcription(service.get(cartTo.getPeriodicalId())));
            }
        }

        return cart;
    }

    @Override
    public List<ShoppingCartShowTo> getAllTO(int id) {
        List<ShoppingCart> list = getAllByUserID(id);
        List<ShoppingCartShowTo> cartShowToList = new ArrayList<>();
        for (ShoppingCart current : list) {
            cartShowToList.add(convert(current));
        }
        return cartShowToList;
    }

    @Override
    public BigDecimal getCost(List<ShoppingCartShowTo> list) {
        BigDecimal price = new BigDecimal(0);
        for (ShoppingCartShowTo current : list) {
            if (current.getFullPrice() != null) {
                price = price.add(current.getFullPrice());
            }
        }
        return price;
    }


    private Map<Integer, Periodical> getMapPeriodicals() {
        List<Periodical> allPeriodicals = service.getAll();
        Map<Integer, Periodical> map = new HashMap<>();
        for (Periodical per : allPeriodicals) {
            map.put(per.getId(), per);
        }
        return map;
    }


    private int getTimeSubscription(@NonNull ShoppingCartTo cartTo) {
        if (cartTo.isFirstHalfYear() && !(cartTo.isSecondHalfYear()) && !(cartTo.isYear())) {
            return 0;
        } else if (cartTo.isSecondHalfYear() && !(cartTo.isYear()) && !(cartTo.isFirstHalfYear())) {
            return 1;
        } else if (cartTo.isYear() && !(cartTo.isFirstHalfYear()) && !(cartTo.isSecondHalfYear())) {
            return 2;
        }
        return 3;
    }

    private ShoppingCartShowTo convert(@NonNull ShoppingCart cart) {
        return asShopingcart(cart);
    }

    private ShoppingCartShowTo asShopingcart(ShoppingCart cart) {
        ShoppingCartShowTo cartShowTo = new ShoppingCartShowTo();
        Periodical periodical = service.get(cart.getPeriodicalId());
        cartShowTo.setIndex(periodical.getIndex());
        cartShowTo.setTitle(periodical.getTitle());
        cartShowTo.setStart(TimeUtil.toString(cart.getStart()));
        cartShowTo.setEnd(TimeUtil.toString(cart.getEnd()));
        cartShowTo.setCountPer(cart.getCountPer());
        cartShowTo.setFullPrice(cart.getPrice());
        return cartShowTo;
    }


}
