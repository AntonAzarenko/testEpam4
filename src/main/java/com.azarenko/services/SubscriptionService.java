package com.azarenko.services;

import com.azarenko.model.Subscription;

import java.util.Date;
import java.util.List;

public interface SubscriptionService {
    void add(int id);

    Subscription getById(int id);

    List<Subscription> getAll();

    List<Subscription> getAllUserByUserId(int id);


}
