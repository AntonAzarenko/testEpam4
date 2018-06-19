package com.azarenko.services;

import com.azarenko.model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public int getUserIdByEmail(String login) {
        return 0;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public boolean isUser(String login) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
