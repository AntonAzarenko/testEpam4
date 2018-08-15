package com.azarenko.repository.datajpa;

import com.azarenko.model.User;
import com.azarenko.repository.UserRepository;
import com.azarenko.repository.datajpa.proxy.ProxyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private ProxyUserRepository proxy;

    @Override
    @Transactional
    public User add(User user) {
        return proxy.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public int getIdByEmail(String login) {
        return proxy.getIdByEmail(login);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll();
    }
}
