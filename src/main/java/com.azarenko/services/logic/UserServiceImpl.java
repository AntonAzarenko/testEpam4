package com.azarenko.services.logic;

import com.azarenko.model.Role;
import com.azarenko.model.User;
import com.azarenko.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public int getIdByEmail(String login) {
        return 0;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean isUser(String login) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean setRole(int id, Role role) {
        return false;
    }
}
