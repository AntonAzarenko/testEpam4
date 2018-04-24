package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.PaymentImplDao;
import com.azarenko.model.Payment;
import com.azarenko.model.ShoppingCart;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final static Logger log = Logger.getLogger(PaymentServiceImpl.class);

    private BaseDao baseDao;
    private ShoppingCartService shoppingCartService;
    private SubscriptionService subscriptionService;

    public PaymentServiceImpl() {
        subscriptionService = new SubscriptionServiceImpl();
        shoppingCartService = new ShoppingCartServiceImpl();
        baseDao = new PaymentImplDao();
    }

    @Override
    public void add(Payment payment) throws DaoException {
        baseDao.add(payment);
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartUser(payment.getUserId());
        for(ShoppingCart pair : shoppingCartList){
            subscriptionService.create(pair.getPeriodicalId(),pair.getUserID(),pair.getStart(),pair.getEnd());
        }
    }

    @Override
    public List<Payment> getPaymentList() throws DaoException {
        return baseDao.getListEntity();
    }

    @Override
    public Payment getPaymentByUserId(int id) {
        return null;
    }

    @Override
    public Payment createPayment(int userId, BigDecimal price) {
        Payment.PaymentBuilder paymentBuilder = new Payment.PaymentBuilder();
        Date date = new Date();
        paymentBuilder.date(date);
        paymentBuilder.userId(userId);
        paymentBuilder.price(price);
        Payment payment = paymentBuilder.build();
        return payment;
    }
}
