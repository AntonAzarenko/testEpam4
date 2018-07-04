package com.azarenko.repository.datajpa;

import com.azarenko.model.User;
import com.azarenko.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
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
