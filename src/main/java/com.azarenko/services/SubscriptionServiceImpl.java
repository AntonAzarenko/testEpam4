package com.azarenko.services;

import com.azarenko.dao.DaoBase;
import com.azarenko.dao.SubscriptionDao;
import com.azarenko.dao.SubscriptionDaoImpl;
import com.azarenko.model.Periodicals;
import com.azarenko.model.Subscription;

import java.util.Date;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDao subscriptionDao;
    private DaoBase daoBase;
    private PeriodicalService periodicalService;

    public SubscriptionServiceImpl() {
        periodicalService = new PeriodicalServiceImpl();
        subscriptionDao = new SubscriptionDaoImpl();
        daoBase = new SubscriptionDaoImpl();
    }

    @Override
    public void create(int idPeriodicals, int userId, Date start, Date end) {
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
    public List<Subscription> getAllSubscription() {
        return daoBase.getListEntity();
    }

    @Override
    public List<Subscription> getAllSubscriptionsUserByUserId(int id) {
       return subscriptionDao.getAllSubscriptionsUserByUserId(id);

    }
}
