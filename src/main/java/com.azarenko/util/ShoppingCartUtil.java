package com.azarenko.util;

import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.SubscriptionTimeUtil;
import com.azarenko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.Date;

@Component
public class ShoppingCartUtil {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionTimeUtil util;

    @Autowired
    private PeriodicalService periodicalService;


    public ShoppingCart create(int periodicalId, int time) {
        Periodical periodical = periodicalService.get(periodicalId);
        return new ShoppingCart(getUserId(), periodicalId, getSart(time),getEnd(),getPrice(periodical),getCountPer(periodical),time);
    }

    private boolean isFirstHalfYear() {
        if (util.getHalfYear() == 0) {
            return true;
        }
        return false;
    }

    private int getUserId(){
        return userService.getIdByEmail("User@ya.ru");//todo
    }

    private BigDecimal getPrice(Periodical periodical){
       return periodical.getPrice();
    }

    private Date getSart(int time){
       return util.getStartDate(time);
    }

    private Date getEnd(){
        if (isFirstHalfYear()) {
            return  util.getEndDate(0);
        }
        return  util.getEndDate(1);
    }

    private int getCountPer(Periodical periodical){
        return util.getNumberOfExist(periodical);
    }
}
