package com.azarenko.services;

import com.azarenko.model.User;

import java.util.List;

public interface UserService {
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
}
