package com.azarenko.repository.mybatis;

import com.azarenko.model.Subscription;
import com.azarenko.repository.SubscriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionRepositoryMapperImpl implements SubscriptionRepository {
    @Override
    public Subscription save(Subscription subscription) {
        return null;
    }

    @Override
    public void save(List<Subscription> subscription) {

    }

    @Override
    public List<Subscription> getAll() {
        return null;
    }

    @Override
    public List<Subscription> getAllByUserID(int userId) {
        return null;
    }

    @Override
    public List<Subscription> getAllByPaymentId(int id) {
        return null;
    }
}
