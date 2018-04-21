package com.azarenko.services;

import com.azarenko.dao.BaseDaoImpl;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserImplDao;
import com.azarenko.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;


public class AuthorisationService extends BaseDaoImpl {
    private final static Logger log = Logger.getLogger(AuthorisationService.class);
    private UserDao userDao;
    private String role;
    private Md5Hash md5Hash;
    private Connection connection;

    public AuthorisationService() {
        userDao = new UserImplDao();
        md5Hash = new Md5Hash();
    }

    public void  setConnection(Connection connection) {
        this.connection = connection;
    }

    public String authorizeUser(String login, String password) {
        setConnection(connection);
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
