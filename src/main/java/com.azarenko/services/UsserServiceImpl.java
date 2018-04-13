package com.azarenko.services;

import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;

public class UsserServiceImpl implements UserService {
    private UserDao userDao;

    public UsserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public int getUserIdByEmail(String login) {
        return userDao.getUserIdByEmail(login);
    }
}
