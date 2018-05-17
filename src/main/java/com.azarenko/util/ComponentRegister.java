package com.azarenko.util;

import com.azarenko.dao.*;
import com.azarenko.services.*;

import java.util.concurrent.ConcurrentHashMap;

public class ComponentRegister {
    public static ConcurrentHashMap<Class<?>, Object> map = new ConcurrentHashMap<>();

    static {
        map.put(PeriodicalService.class, new PeriodicalServiceImpl());
        map.put(UserService.class, new UserServiceImpl());
        map.put(PaymentService.class, new PaymentServiceImpl());
        map.put(SubscriptionService.class, new SubscriptionServiceImpl());
        map.put(ShoppingCartService.class, new ShoppingCartServiceImpl());
        map.put(PaymentDao.class, new PaymentDaoImpl());
        map.put(PeriodicalsDao.class, new PeriodicalsDaoImpl());
        map.put(ShoppingCartDao.class, new ShoppingCartDaoImpl());
        map.put(SubscriptionDao.class, new SubscriptionDaoImpl());
        map.put(UserDao.class, new UserServiceImpl());
        map.put(AuthorisationService.class, new AuthorisationService());
    }

    public Object getImpl(Class<?> impl) {
        if (impl.equals(JdbcTransactionImpl.class)) {
            return new JdbcTransactionImpl();
        } else {
            if (map.containsKey(impl)) {
                return map.get(impl);
            }
        }
        return null;
    }
}
