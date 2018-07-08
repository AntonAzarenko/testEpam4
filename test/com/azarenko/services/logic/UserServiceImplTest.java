package com.azarenko.services.logic;

import com.azarenko.model.User;
import com.azarenko.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserService service;
    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("User@ya.ru");
        LOG.info(user.toString());

    }

    @Test
    public void getIdByEmail() throws Exception {
        int id = service.getIdByEmail("User@ya.ru");
        LOG.info(String.valueOf(id));
    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        service.getAll().forEach(m->LOG.info(String.valueOf(m)));
    }

}