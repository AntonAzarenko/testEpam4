package com.azarenko.dao;

import com.azarenko.model.User;

import java.util.List;

/**
 * @author Anton Azarenko
 */
public interface UserDao  extends BaseDao<User> {

    User getUserByEmail(String email) throws DaoException;


    int getUserIdByEmail(String login) throws DaoException;

    @Override
    List getListEntity() throws DaoException;


}
