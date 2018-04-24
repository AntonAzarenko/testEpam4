package com.azarenko.services;

import com.azarenko.dao.DaoException;
import com.azarenko.model.User;

public interface UserService  {
    User getUserByEmail(String email) throws DaoException;
    int getUserIdByEmail(String login);
    void addUser(User user) throws DaoException;
}
