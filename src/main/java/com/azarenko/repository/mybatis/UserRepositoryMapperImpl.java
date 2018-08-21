package com.azarenko.repository.mybatis;

import com.azarenko.model.User;
import com.azarenko.repository.UserRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class UserRepositoryMapperImpl  implements UserRepository {


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public UserRepositoryMapperImpl() {
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
       return sqlSessionFactory.openSession().selectOne("com.azarenko.repository.UserRepository.getByEmail", email);
    }

    @Override
    public int getIdByEmail(String login) {
        return 0;
    }

    @Override
    public List<User> getAll() {
        return null;
    }


}
