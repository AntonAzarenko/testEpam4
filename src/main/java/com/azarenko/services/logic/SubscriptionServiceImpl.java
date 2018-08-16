package com.azarenko.services.logic;

import com.azarenko.model.Payment;
import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;
import com.azarenko.repository.SubscriptionRepository;
import com.azarenko.services.PaymentService;
import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.SubscriptionService;
import com.azarenko.to.SubscriptionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    @Autowired
    SubscriptionTo subscriptionTo;

    @Autowired
    PaymentService paymentService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public void add(int id) {
        List<ShoppingCart> cart = shoppingCartService.getAllByUserID(id);
        List<Subscription> subscriptionList = new ArrayList<>();
        paymentService.add(paymentService.create(id));
        int paymentId = getPaymentID(id);
        for (ShoppingCart current : cart) {
            subscriptionList.add(create(current, paymentId));
        }
        repository.save(subscriptionList);
        shoppingCartService.removeShoppingCartUser(id);

    }

    private int getPaymentID(int userId) {
        List<Payment> list = paymentService.getPaymentByUserId(userId);
        LocalDate date = LocalDateTime.now().toLocalDate();
        for (Payment current : list) {
            LocalDate currentDay = current.getDate().toLocalDate();
            if (currentDay.equals(date)) {
                return current.getId();
            }
        }
        return 0;
    }

    private Subscription create(ShoppingCart cart, int paymentId) {
        return new Subscription(cart.getPeriodicalId(), cart.getUserID(), cart.getStart(), cart.getEnd(), paymentId);
    }

    @Override
    public Subscription getById(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAll() {
        return null;
    }

    @Override
    public List<Subscription> getAllByUserId(int id) {
        return repository.getAllByUserID(id);
    }

    @Override
    public List<SubscriptionTo> getAllUByUserId(List<Subscription> list) {
        List<SubscriptionTo> subscriptionToList = new ArrayList<>();
        for (Subscription current : list) {
            subscriptionToList.add(subscriptionTo.asSubscription(current));
        }
        return subscriptionToList;
    }

    @Override
    public List<Subscription> getAllByPaymentId(int id) {
        return repository.getAllByPaymentId(id);
    }
}
