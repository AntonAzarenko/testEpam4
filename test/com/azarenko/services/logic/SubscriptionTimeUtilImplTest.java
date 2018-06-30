package com.azarenko.services.logic;

import com.azarenko.services.PeriodicalService;
import com.azarenko.services.SubscriptionTimeUtil;
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
public class SubscriptionTimeUtilImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionTimeUtilImplTest.class);

    @Autowired
    private SubscriptionTimeUtil subscriptionTimeUtil;

    @Autowired
    private PeriodicalService service;

    @Test
    public void getHalfYear() throws Exception {
        LOG.info(String.valueOf(subscriptionTimeUtil.getHalfYear()));
    }

    @Test
    public void getNumberOfExist() throws Exception {
      LOG.info(String.valueOf(subscriptionTimeUtil.getNumberOfExist(service.get(6))));
    }

    @Test
    public void getCountDaysInHalfYEar() throws Exception {
        LOG.info(String.valueOf(subscriptionTimeUtil.getCountDaysInHalfYEar(subscriptionTimeUtil.getHalfYear())));
    }

    @Test
    public void getDay() throws Exception {
    }

    @Test
    public void getMonth() throws Exception {
    }

    @Test
    public void getYear() throws Exception {
    }

}