package com.azarenko.services.logic;

import com.azarenko.model.Role;
import com.azarenko.model.User;
import com.azarenko.repository.UserRepository;
import com.azarenko.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public int getIdByEmail(String login) {
        return userRepository.getIdByEmail(login);
    }

    @Override
    @Transactional
    public boolean add(User user) {
         return userRepository.add(user) != null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
