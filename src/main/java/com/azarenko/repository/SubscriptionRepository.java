package com.azarenko.repository;

import com.azarenko.model.Subscription;

import java.util.List;

public interface SubscriptionRepository {
    Subscription save(Subscription subscription);

    void save(List<Subscription> subscription);

    List<Subscription> getAll();

    List<Subscription> getAllByUserID(int userId);

    List<Subscription> getAllByPaymentId(int id);


}
