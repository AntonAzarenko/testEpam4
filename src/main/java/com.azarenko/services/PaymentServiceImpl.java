package com.azarenko.services;

import com.azarenko.dao.DaoBase;
import com.azarenko.dao.PaymentDao;
import com.azarenko.dao.PaymentDaoImpl;
import com.azarenko.model.Payment;
import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final static Logger log = Logger.getLogger(PaymentServiceImpl.class);

    private DaoBase daoBase;
    private ShoppingCartService shoppingCartService;
    private SubscriptionService subscriptionService;

    public PaymentServiceImpl() {
        subscriptionService = new SubscriptionServiceImpl();
        shoppingCartService = new ShoppingCartServiceImpl();
        daoBase = new PaymentDaoImpl();
    }

    @Override
    public void add(Payment payment) {
        daoBase.add(payment);
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartUser(payment.getUserId());
        for(ShoppingCart pair : shoppingCartList){
            subscriptionService.create(pair.getPeriodicalId(),pair.getUserID(),pair.getStart(),pair.getEnd());
        }
    }

    @Override
    public List<Payment> getPaymentList() {
        return daoBase.getListEntity();
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
