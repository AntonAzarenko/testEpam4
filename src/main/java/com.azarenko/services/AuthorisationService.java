package com.azarenko.services;

import com.azarenko.dao.BaseDaoImpl;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;


public class AuthorisationService extends BaseDaoImpl {
    private Md5Hash md5Hash;

    public AuthorisationService() {
        md5Hash = new Md5Hash();
    }

    public String authorizeUser(String login, String password) throws ServiceException, DaoException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(login);
        if (user == null) {
            return "";
        }
        String hexPassword = md5Hash.getMd5Hash(password);
        if (user.getPassword().equals(hexPassword)) return user.getRole();
        return "";
    }
}
