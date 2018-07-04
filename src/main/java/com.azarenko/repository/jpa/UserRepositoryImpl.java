package com.azarenko.repository.jpa;

import com.azarenko.model.User;
import com.azarenko.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User add(User user) {
        if(user.getId() == null){
            em.persist(user);
        }else{
            em.merge(user);
        }
        return user;
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
