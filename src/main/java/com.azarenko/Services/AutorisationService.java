package com.azarenko.Services;

import com.azarenko.Services.UserService;
import com.azarenko.Services.UserServiceImpl;
import com.azarenko.model.Users;

import java.util.List;

public class AutorisationService {

    private UserService userService;
    private String role;

    public AutorisationService() {
        userService = new UserServiceImpl();
    }

    public String authorizeUser(String login, String password){
        List<Users> usersList = userService.getUserList();
        for(Users us : usersList){
            if(us.getEmail().equals(login)){
                if(us.getPassword().equals(password)){
                    role = us.getRole();
                    return role;
                }else {
                    System.out.println("Введенный пароль не совпадает");
                    continue;
                }

            }else {
                System.out.println("Такого пользователя не существует");
                continue;

            }
        }
        return role;
    }
}
