package com.azarenko.dao;

import com.azarenko.model.User;

import java.util.List;

/**
 * @author Anton Azarenko
 */
public interface UserDao  extends BaseDao {


    /**
     * Get user by Email
     * @param email
     * @return
     */
    User getUserByEmail(String email);


    int getUserIdByEmail(String login);

    @Override
    List getListEntity();
}
