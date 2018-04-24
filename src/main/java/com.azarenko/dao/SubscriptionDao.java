package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Subscription;

import java.util.List;

public interface SubscriptionDao  extends BaseDao<Subscription> {

    List<Subscription> getAllSubscriptionsUserByUserId(int id) throws DaoException;

    @Override
    List getListEntity() throws DaoException;
}
