package com.azarenko.services;

import com.azarenko.model.Periodical;
import com.azarenko.web.controllers.PeriodicalRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class PeriodicalServiceImplTest {
    @Autowired
    private PeriodicalRestController restController;
    @Test
    public void add() throws Exception {
        Periodical periodical = new Periodical(null,"spring","Spring",5,new BigDecimal(2));
        restController.add(periodical);
    }

}