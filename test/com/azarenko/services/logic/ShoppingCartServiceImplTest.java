package com.azarenko.services.logic;

import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionTimeUtil;
import com.azarenko.to.ShoppingCartShowTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartServiceImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartServiceImplTest.class);
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SubscriptionTimeUtil timeUtil;

    @Autowired
    private PeriodicalService periodicalService;

    @Test
    public void add() throws Exception {
        List<Periodical> list = periodicalService.getAll();
        ShoppingCart cart = new ShoppingCart();
        cart.setUserID(1);
        cart.setPeriodicalId(list.get(2).getId());
        cart.setPrice(list.get(2).getPrice());
        if (timeUtil.getHalfYear() == 1) {
            cart.setStart(new Date());
            cart.setTime(1);
            cart.setEnd(timeUtil.getEndDate(cart.getTime()));
        }

        cart.setCountPer(timeUtil.getNumberOfExist(list.get(2)));
        LOG.info(cart.toString());
        shoppingCartService.add(cart);

        cart = new ShoppingCart();
        cart.setUserID(1);
        cart.setPeriodicalId(list.get(6).getId());
        cart.setPrice(list.get(6).getPrice());
        if (timeUtil.getHalfYear() == 1) {
            cart.setStart(new Date());
            cart.setTime(1);
            cart.setEnd(timeUtil.getEndDate(cart.getTime()));
        }
        cart.setCountPer(timeUtil.getNumberOfExist(list.get(6)));
        shoppingCartService.add(cart);
    }

    @Test
    public void getAllByUserID() throws Exception {
        List<ShoppingCart> list = shoppingCartService.getAllByUserID(1);
        if (list != null) {
            for (ShoppingCart carts : list) {
                LOG.info(carts.toString());
            }
        }
    }

    @Test
    public void getPriceForSubcription() throws Exception {
        List<Periodical> list = periodicalService.getAll();
        LOG.info(String.valueOf(shoppingCartService.getPriceForSubcription(list.get(2))));
        LOG.info(String.valueOf(shoppingCartService.getPriceForSubcription(list.get(6))));

    }

    @Test
    public void getFullPriceForPayment() throws Exception {
        LOG.info(String.valueOf(shoppingCartService.getFullPriceForPayment(1)));
    }

    @Test
    public void removeShoppingCartUser() throws Exception {
        shoppingCartService.removeShoppingCartUser(1);
    }

    @Test
    public void getMapPeriodicals() throws Exception {
    }
    @Test
    public void get(){

        if(shoppingCartService.getByPeriodicalID(2)){
            LOG.info("true");
        }else {
            LOG.info("false");
        }
    }




}