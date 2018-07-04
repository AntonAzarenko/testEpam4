package com.azarenko.repository;

import com.azarenko.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {
    User add(User user);

    User getByEmail(String email);

    int getIdByEmail(String login);

    List<User> getAll();

}
