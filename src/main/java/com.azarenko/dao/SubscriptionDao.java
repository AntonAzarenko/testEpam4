package com.azarenko.dao;

import com.azarenko.model.Subscription;

import java.util.List;

public interface SubscriptionDao {
    void create(Subscription subscription);
    Subscription getSubscriptionByUserId(int id);
    List<Subscription> getAllSubscription();
}
