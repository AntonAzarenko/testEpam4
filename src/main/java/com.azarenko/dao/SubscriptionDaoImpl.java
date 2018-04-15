package com.azarenko.dao;

import com.azarenko.model.Subscription;
import com.azarenko.util.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {
    private final static Logger log = Logger.getLogger(SubscriptionDaoImpl.class);
    private Connection connection;

    public SubscriptionDaoImpl() {
        connection = DBUtil.getConnection();
    }

    @Override
    public void create(Subscription subscription) {

    }

    @Override
    public Subscription getSubscriptionByUserId(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return null;
    }
}
