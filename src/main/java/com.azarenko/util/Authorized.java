package com.azarenko.util;

import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.Users;

import java.util.ArrayList;
import java.util.List;

public class Authorized {

    private UserDao userDao;
    private String role;

    public Authorized() {
        userDao = new UserDaoImpl();
    }

    public String authorizeUser(String login, String password){
        List<Users> usersList = userDao.getUserList();
        for(Users us : usersList){
            if(us.getEmail().equals(login)){
                if(us.getPassword().equals(password)){
                    role = us.getRole();
                    return role;
                }else {
                    System.out.println("Введенный пароль не совпадает");
                    return "Введенный пароль не совпадает";}
            }else {
                System.out.println("Такого пользователя не существует");
                return "Такого пользователя не существует";
            }
        }
        return role;
    }
}
