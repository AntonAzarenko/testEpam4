package com.azarenko.services;

import com.azarenko.dao.BaseDaoImpl;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;


public class AuthorisationService extends BaseDaoImpl {
    private final static Logger log = Logger.getLogger(AuthorisationService.class);

    private String role;
    private Md5Hash md5Hash;

    public AuthorisationService() {

        md5Hash = new Md5Hash();
    }


    public String authorizeUser(String login, String password) throws ServiceException, DaoException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(login);
        if (user == null) {
            return role = "";
        }
        String hexPassword = md5Hash.getMd5Hash(password);
        if (user.getPassword().equals(hexPassword)) {
            return role = user.getRole();
        }
        return role = "";
    }
}
