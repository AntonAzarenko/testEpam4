package com.azarenko.services;

import com.azarenko.model.Role;
import com.azarenko.model.User;

import java.util.List;

public interface UserService {
    User getByEmail(String email);

    int getIdByEmail(String login);

    boolean add(User user);

    List<User> getAll();


}
