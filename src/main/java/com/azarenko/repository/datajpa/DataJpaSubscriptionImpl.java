package com.azarenko.repository.datajpa;

import com.azarenko.model.Subscription;
import com.azarenko.repository.SubscriptionRepository;
import com.azarenko.repository.datajpa.proxy.ProxySubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaSubscriptionImpl implements SubscriptionRepository {

    @Autowired
    private ProxySubscriptionRepository proxy;

    @Override
    public Subscription save(Subscription subscription) {
        return proxy.save(subscription);
    }

    @Override
    public void save(List<Subscription> subscription) {
        for (Subscription current: subscription){
            proxy.save(current);
        }

    }

    @Override
    public List<Subscription> getAll() {
        return null;
    }

    @Override
    public List<Subscription> getAllByUserID(int userId) {
        return proxy.getAllByUserId(userId);
    }

    @Override
    public List<Subscription> getAllByPaymentId(int id) {
        return proxy.getAllByPaymentId(id);
    }
}
