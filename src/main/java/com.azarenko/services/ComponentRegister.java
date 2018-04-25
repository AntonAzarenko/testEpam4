package com.azarenko.services;

import java.util.concurrent.ConcurrentHashMap;

public class ComponentRegister {
    public static ConcurrentHashMap<Class<?>, Object> map = new ConcurrentHashMap<>();

    static {
        map.put(PeriodicalService.class, new PeriodicalServiceImplImpl());
        map.put(UserService.class, new UserServiceImpl());
    }

    public Object getImpl(Class<?> impl) {
        if (map.containsKey(impl)) {
            return map.get(impl);
        }
        return null;
    }
}
