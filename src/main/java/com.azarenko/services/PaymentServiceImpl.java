package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.PaymentDaoImpl;
import com.azarenko.model.Payment;
import com.azarenko.model.ShoppingCart;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public void add(Payment payment) throws DaoException, ServiceException {
        BaseDao<Payment> baseDao = new PaymentDaoImpl();
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
        baseDao.add(payment);
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartUser(payment.getUserId());
        for(ShoppingCart pair : shoppingCartList){
            subscriptionService.create(pair.getPeriodicalId(),pair.getUserID(),pair.getStart(),pair.getEnd());
        }
    }

    @Override
    public List<Payment> getPaymentList() throws DaoException {
        BaseDao<Payment> baseDao = new PaymentDaoImpl();
        return baseDao.getListEntity();
    }

    @Override
    public Payment getPaymentByUserId(int id) {
        return null;
    }


}
