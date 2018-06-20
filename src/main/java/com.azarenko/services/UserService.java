package com.azarenko.services;

import com.azarenko.model.Role;
import com.azarenko.model.User;

import java.util.List;

public interface UserService {
    User getByEmail(String email);

    int getIdByEmail(String login);

    boolean addUser(User user);

    boolean isUser(String login);

    List<User> getAll();

    boolean setRole(int id, Role role);
}
