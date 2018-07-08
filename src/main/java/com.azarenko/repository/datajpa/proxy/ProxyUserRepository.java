package com.azarenko.repository.datajpa.proxy;

import com.azarenko.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProxyUserRepository extends JpaRepository<User, Integer> {
    @Override
    User save (User user);

    @Transactional
    int deleteById(int id);

    User getByEmail(String email);

    int getIdByEmail(String Email);

    @Override
    List<User> findAll();
}
