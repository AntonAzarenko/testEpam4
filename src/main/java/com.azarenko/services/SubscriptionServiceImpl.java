package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.SubscriptionDao;
import com.azarenko.dao.SubscriptionImplDao;
import com.azarenko.model.Periodicals;
import com.azarenko.model.Subscription;

import java.util.Date;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public void create(int idPeriodicals, int userId, Date start, Date end) throws ServiceException, DaoException {
        SubscriptionDao subscriptionDao = new SubscriptionImplDao();
        PeriodicalService periodicalService = new PeriodicalServiceImpl();
        Subscription.SubscriptionBuild subscriptionBuild = new Subscription.SubscriptionBuild();
        subscriptionBuild.idPeriodical(idPeriodicals);
        Periodicals periodicals = periodicalService.getPeriodical(idPeriodicals);
        subscriptionBuild.namePeriodical(periodicals.getTitle());
        subscriptionBuild.dateStartSubcription(start);
        subscriptionBuild.dateEndSubscription(end);
        subscriptionBuild.userId(userId);
        Subscription subscription = subscriptionBuild.build();
        subscriptionDao.add(subscription);
    }

    @Override
    public Subscription getSubscriptionByUserId(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscription() throws DaoException {
        BaseDao<Subscription> baseDao = new SubscriptionImplDao();
        return baseDao.getListEntity();
    }

    @Override
    public List<Subscription> getAllSubscriptionsUserByUserId(int id) throws DaoException {
        SubscriptionDao subscriptionDao = new SubscriptionImplDao();
        return subscriptionDao.getAllSubscriptionsUserByUserId(id);

    }
}
