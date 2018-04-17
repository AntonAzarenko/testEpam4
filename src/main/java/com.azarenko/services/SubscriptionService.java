package com.azarenko.services;

import com.azarenko.model.Subscription;

import java.util.Date;
import java.util.List;

public interface SubscriptionService {
    void create(int idPeriodicals, int userId, Date start, Date end);

    Subscription getSubscriptionByUserId(int id);

    List<Subscription> getAllSubscription();

    List<Subscription> getAllSubscriptionsUserByUserId(int id);

}
