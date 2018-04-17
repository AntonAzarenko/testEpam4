package com.azarenko.services;

import com.azarenko.dao.*;
import com.azarenko.model.Periodicals;
import com.azarenko.model.ShoppingCart;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final static Logger log = Logger.getLogger(ShoppingCartServiceImpl.class);

    private ShoppingCartDao shoppingCartDao;
    private DaoBase daoBase;

    public ShoppingCartServiceImpl() {
        daoBase = new PeriodicalsDaoImpl();
        shoppingCartDao = new ShoppingCartDaoImpl();
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void goToPay() {

    }

    @Override
    public List<ShoppingCart> getShoppingCartUser(int id) {
        return shoppingCartDao.getShoppingCartByUserId(id);

    }


    @Override
    public BigDecimal getPriceForSubcription(int id, Date start, Date end) {
        List<Periodicals> periodicalsList = daoBase.getListEntity();
        Date startD = new Date();
        Date endD = new Date();
        BigDecimal newPrice = null;
        for (Periodicals pair : periodicalsList) {
            if (pair.getId() == id) {
                startD = start;
                endD = end;
                int outputFrequncy = getOutputFrequency(id);
                BigDecimal price = getPrice(id);
                newPrice = calculatingPrice(price, start, end, outputFrequncy);
            }
        }
        return newPrice;
    }

    @Override
    public BigDecimal getFullPriceForPayment(int userId) {
        List<ShoppingCart> cartList = getShoppingCartUser(userId);
        BigDecimal fullPrice = new BigDecimal(0);
        for (ShoppingCart pair : cartList) {
            fullPrice = fullPrice.add(pair.getPrice());
        }
        return fullPrice;
    }

    @Override
    public void removeShoppingCartUser(int id) {
        shoppingCartDao.removeShoppingCartUser(id);
    }

    private BigDecimal calculatingPrice(BigDecimal price, Date start, Date end, int outFreq) {
        Calendar startC = Calendar.getInstance();
        Calendar endC = Calendar.getInstance();
        startC.setTime(start);
        endC.setTime(end);
        int days = endC.get(Calendar.DAY_OF_YEAR) - startC.get(Calendar.DAY_OF_YEAR);
        double t = (double) days / 30;
        BigDecimal countOutput = new BigDecimal(t);
        log.info(days);
        log.info(t);
        log.info(price);
        BigDecimal totalPrice = price.multiply(countOutput);
        return totalPrice;
    }

    private int getOutputFrequency(int id) {
        List<Periodicals> periodicalsList = daoBase.getListEntity();
        for (Periodicals pair : periodicalsList) {
            if (id == pair.getId()) {
                return pair.getOutputFrequency();
            }
        }
        return 0;
    }

    private BigDecimal getPrice(int id) {
        List<Periodicals> periodicalsList = daoBase.getListEntity();
        for (Periodicals pair : periodicalsList) {
            if (id == pair.getId()) {
                return pair.getPrice();
            }
        }
        return null;
    }


}
