package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private BaseDao baseDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        baseDao = new UserDaoImpl();
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        return userDao.getUserByEmail(email);
    }

    @Override
    public int getUserIdByEmail(String login) {
        return userDao.getUserIdByEmail(login);
    }

    @Override
    public void addUser(User user) throws DaoException {
        baseDao.add(user);
    }
}
