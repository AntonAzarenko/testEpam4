package com.azarenko.services;

import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;

public class AutorisationService {

    private UserDao userDao;
    private String role;

    public AutorisationService() {
        userDao = new UserDaoImpl();
    }

    public String authorizeUser(String login, String password) {
        User user = userDao.getUserByEmail(login);
        if (user.getPassword().equals(password)){
            return role  = user.getRole();
        }
        return role="";
    }
}
