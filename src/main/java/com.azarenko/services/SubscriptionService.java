package com.azarenko.services;

import com.azarenko.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    void create(Subscription subscription);
    Subscription getSubscriptionByUserId(int id);
    List<Subscription> getAllSubscription();

}
