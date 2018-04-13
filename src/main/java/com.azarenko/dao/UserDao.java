package com.azarenko.dao;

import com.azarenko.model.User;

import java.util.List;

public interface UserDao {
    /**
     * Get all User list
      * @return
     */
    List<User> getUserList();

    /**
     * Get user by Email
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * Get user by Id
     * @param id
     * @return
     */
    User getUserByID(int id);

    int getUserIdByEmail(String login);
}
