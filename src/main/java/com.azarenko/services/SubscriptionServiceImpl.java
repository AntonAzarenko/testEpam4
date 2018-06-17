package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.SubscriptionDao;
import com.azarenko.dao.SubscriptionDaoImpl;
import com.azarenko.exceptions.ServiceException;
import com.azarenko.model.Periodical;
import com.azarenko.model.Subscription;
import com.azarenko.util.ComponentRegister;

import java.util.Date;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public void create(int idPeriodicals, int userId, Date start, Date end) throws ServiceException, DaoException {
        SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
        PeriodicalService periodicalService = new PeriodicalServiceImpl();
        Subscription.SubscriptionBuild subscriptionBuild = new Subscription.SubscriptionBuild();
        subscriptionBuild.idPeriodical(idPeriodicals);
        Periodical periodical = periodicalService.getPeriodical(idPeriodicals);
        subscriptionBuild.namePeriodical(periodical.getTitle());
        subscriptionBuild.dateStartSubcription(start);
        subscriptionBuild.dateEndSubscription(end);
        subscriptionBuild.userId(userId);
        Subscription subscription = subscriptionBuild.build();
        subscriptionDao.add(subscription);
    }

    @Override
    public Subscription getSubscriptionById(int id) throws DaoException {
        ComponentRegister register = new ComponentRegister();
        SubscriptionDao dao = (SubscriptionDao) register.getImpl(SubscriptionDao.class);
        return dao.getEntityById(id);
    }

    @Override
    public List<Subscription> getAllSubscription() throws DaoException {
        BaseDao<Subscription> baseDao = new SubscriptionDaoImpl();
        return baseDao.getListEntity();
    }

    @Override
    public List<Subscription> getAllSubscriptionsUserByUserId(int id) throws DaoException {
        SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();
        return subscriptionDao.getAllSubscriptionsUserByUserId(id);

    }
}
