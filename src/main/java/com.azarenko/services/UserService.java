package com.azarenko.services;

import com.azarenko.model.User;

public interface UserService {

    User getUserByEmail(String email);
    int getUserIdByEmail(String login);
}
