package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Subscription;

import java.util.List;

public interface SubscriptionDao  extends DaoBase{

    List<Subscription> getAllSubscriptionsUserByUserId(int id);

    @Override
    AbstractBaseEntity getEntityById(int id);

    @Override
    void add(AbstractBaseEntity entity);

    @Override
    List getListEntity();
}
