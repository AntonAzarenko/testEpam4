package com.azarenko.services;

import com.azarenko.dao.*;
import com.azarenko.model.Periodical;
import com.azarenko.model.ShoppingCart;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final static Logger log = Logger.getLogger(ShoppingCartServiceImpl.class);

    @Override
    public void add(ShoppingCart shoppingCart) throws DaoException {
        int outputFrequency = getOutputFrequency(shoppingCart.getPeriodicalId());
        int countDays = (30 / outputFrequency) * shoppingCart.getCountPer();
        Date start = shoppingCart.getStart();
        if (start == null) {
            start = new Date();
            shoppingCart.setStart(start
            );
        }
        shoppingCart.setEnd(getEndDate(countDays, start));
        BigDecimal price = getPriceForSubcription(shoppingCart.getPeriodicalId());
        BigDecimal pr = price.multiply(new BigDecimal(shoppingCart.getCountPer()));
        shoppingCart.setPrice(pr);
        BaseDao<ShoppingCart> baseDao = new ShoppingCartDaoImpl();
        baseDao.add(shoppingCart);
    }

    private Date getEndDate(int countDays, Date start) {
        Calendar startC = Calendar.getInstance();
        startC.setTime(start);
        startC.add(Calendar.DATE, countDays);
        start = startC.getTime();
        return start;
    }


    @Override
    public List<ShoppingCart> getShoppingCartUser(int id) throws DaoException {
        ShoppingCartDao cartDao = new ShoppingCartDaoImpl();
        return cartDao.getShoppingCartByUserId(id);

    }

    @Override
    public BigDecimal getPriceForSubcription(int periodicalId) throws DaoException {
        BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        Periodical periodical = baseDao.getEntityById(periodicalId);
        return periodical.getPrice();
    }

    @Override
    public BigDecimal getFullPriceForPayment(int userId) throws DaoException {
        List<ShoppingCart> cartList = getShoppingCartUser(userId);
        BigDecimal fullPrice = new BigDecimal(0);
        for (ShoppingCart pair : cartList) {
            fullPrice = fullPrice.add(pair.getPrice());
        }
        return fullPrice;
    }

    @Override
    public void removeShoppingCartUser(int id) throws DaoException {
        ShoppingCartDao cartDao = new ShoppingCartDaoImpl();
        cartDao.removeShoppingCartUser(id);
    }


    private int getOutputFrequency(int id) throws DaoException {
        BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        List<Periodical> periodicalList = baseDao.getListEntity();
        for (Periodical pair : periodicalList) {
            if (id == pair.getId()) {
                return pair.getOutputFrequency();
            }
        }
        return 0;
    }

    // ToDo ???
    private BigDecimal getPrice(int id) throws DaoException {
        BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        List<Periodical> periodicalList = baseDao.getListEntity();
        for (Periodical pair : periodicalList) {
            if (id == pair.getId()) {
                return pair.getPrice();
            }
        }
        return null;
    }


}
