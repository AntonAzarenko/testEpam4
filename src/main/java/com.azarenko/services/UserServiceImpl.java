package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
        private final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User getUserByEmail(String email) throws DaoException {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserByEmail(email);
    }

    @Override
    public int getUserIdByEmail(String login) throws DaoException {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserIdByEmail(login);
    }

    @Override
    public void addUser(User user) throws DaoException {
        BaseDao baseDao = new UserDaoImpl();
        baseDao.add(user);
    }

    @Override
    public boolean isUser(String login) throws DaoException {
        BaseDao baseDao = new UserDaoImpl();
        List<User> userList = baseDao.getListEntity();
        for(User current : userList){
            log.info(current.getEmail());
            log.info(login);
            if(current.getEmail().equals(login)){
                return true;
            }
        }
        return false;
    }
}
