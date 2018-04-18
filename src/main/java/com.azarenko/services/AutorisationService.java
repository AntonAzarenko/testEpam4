package com.azarenko.services;

import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.User;

public class AutorisationService {

    private UserDao userDao;
    private String role;
    private Md5Hash md5Hash;

    public AutorisationService() {
        userDao = new UserDaoImpl();
        md5Hash = new Md5Hash();
    }

    public String authorizeUser(String login, String password) {
        User user = userDao.getUserByEmail(login);
        if(user == null){
            return role = "";
        }
        String hexPassword = md5Hash.getMd5Hash(password);
        if (user.getPassword().equals(hexPassword)){
            return role  = user.getRole();
        }
        return role="";
    }
}
