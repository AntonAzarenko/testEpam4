package com.azarenko.services;

import com.azarenko.dao.SubscriptionDao;
import com.azarenko.dao.SubscriptionDaoImpl;
import com.azarenko.model.Subscription;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDao subscriptionDao;

    public SubscriptionServiceImpl() {
        subscriptionDao = new SubscriptionDaoImpl();
    }

    @Override
    public void create(Subscription subscription) {
        subscriptionDao.create(subscription);
    }

    @Override
    public Subscription getSubscriptionByUserId(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return null;
    }
}
