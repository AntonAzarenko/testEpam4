package com.azarenko.services.logic;

import com.azarenko.model.Periodical;
import com.azarenko.services.HistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class HistoryServiceImplTest {
    @Autowired
    private HistoryService service;
    @Test
    public void getAllByPeriodicalId() throws Exception {
    }

    @Test
    public void searchAllByName() throws Exception {
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void searchBetween() throws Exception {
    }

}