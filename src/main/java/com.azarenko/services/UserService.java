package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email) throws DaoException;

    int getUserIdByEmail(String login) throws DaoException;

    void addUser(User user) throws DaoException;

    boolean isUser(String login) throws DaoException;

    List<User> getAllUsers() throws  DaoException;
}
