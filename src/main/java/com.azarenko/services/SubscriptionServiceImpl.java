package com.azarenko.services;

import com.azarenko.model.Subscription;

import java.util.Date;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public void create(int idPeriodicals, int userId, Date start, Date end) {

    }

    @Override
    public Subscription getSubscriptionById(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptionsUserByUserId(int id) {
        return null;
    }
}
