package com.azarenko.services;

import com.azarenko.dao.DaoBase;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private DaoBase daoBase;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        daoBase = new UserDaoImpl();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public int getUserIdByEmail(String login) {
        return userDao.getUserIdByEmail(login);
    }

    @Override
    public void addUser(User user) {
        daoBase.add(user);
    }
}
