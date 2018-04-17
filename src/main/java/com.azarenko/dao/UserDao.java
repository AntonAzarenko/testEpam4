package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.User;

import java.util.List;

/**
 * @author Anton Azarenko
 */
public interface UserDao  extends DaoBase{


    /**
     * Get user by Email
     * @param email
     * @return
     */
    User getUserByEmail(String email);


    int getUserIdByEmail(String login);

    @Override
    AbstractBaseEntity getEntityById(int id);

    @Override
    void add(AbstractBaseEntity entity);

    @Override
    List getListEntity();
}
