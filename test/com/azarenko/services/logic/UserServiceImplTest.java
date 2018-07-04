package com.azarenko.services.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Test
    public void getByEmail() throws Exception {
    }

    @Test
    public void getIdByEmail() throws Exception {
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

}