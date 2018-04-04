package com.azarenko.services;

import com.azarenko.model.User;

public class AutorisationService {

    private UserService userService;
    private String role;

    public AutorisationService() {
        userService = new UserServiceImpl();
    }

    public String authorizeUser(String login, String password) {
        User user = userService.getUserByEmail(login);
        if (user.getPassword().equals(password)){
            return role  = user.getRole();
        }
        return role="";
    }
}
