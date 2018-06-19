package com.azarenko.services;

import com.azarenko.model.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);

    int getUserIdByEmail(String login);

    void addUser(User user);

    boolean isUser(String login);

    List<User> getAllUsers();
}
