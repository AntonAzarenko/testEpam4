package com.azarenko.services;

import com.azarenko.model.Subscription;
import com.azarenko.to.SubscriptionTo;

import java.util.Date;
import java.util.List;

public interface SubscriptionService {
    void add(int id);

    Subscription getById(int id);

    List<Subscription> getAll();

    List<Subscription> getAllByUserId(int id);

    List<SubscriptionTo> getAllUByUserId(List<Subscription> list);

    List<Subscription> getAllByPaymentId(int id);


}
