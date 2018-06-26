package com.azarenko.services.logic;

import com.azarenko.exceptions.NotFoundException;
import com.azarenko.model.Periodical;
import com.azarenko.services.PeriodicalService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class PeriodicalServiceImplTest {
   private static final Logger LOG = getLogger(PeriodicalServiceImplTest.class);
    @Autowired
    private PeriodicalService periodicalService;

    @Test
    public void getAll() throws Exception {
        List<Periodical> periodicals = periodicalService.getAll();
        if(periodicals.size() > 0){
            LOG.info("get all status OK");
        }
        periodicals.forEach(p -> System.out.println(p));
    }

    @Test
    public void save() throws Exception {
        Periodical periodical =
                new Periodical(null,"Springs",
                        "How to learn the SPRING","Azarenko)",24,32568,18,new BigDecimal(2.00));
        periodicalService.save(periodical);
       /* Periodical periodical2 =
                new Periodical(27,"Spring",
                        "How to learn the SPRING","Azarenko)",24,32568,18,new BigDecimal(2.20));
       periodicalService.save(periodical2);*/
        Periodical periodical3 =
                new Periodical(27,"Spring",
                        "How to learn the SPRING","Azarenko)",24,32568,18,new BigDecimal(2.25));
        periodicalService.save(periodical3);
    }
    @Before
    public void before(){
        LOG.info("spring  before test");
    }

    @After
    public void after(){
        LOG.info("spring after test");
    }


    @Test()
    public void remove() throws Exception {
        if (periodicalService.remove(17)) {
            LOG.info("remove element");
        } else {
            LOG.info("didn't remove");
        }
    }

    @Test
    public void get() throws Exception {
        Periodical  periodical = periodicalService.get(9);
        LOG.info(periodical.getTitle());
    }


    @Test
    public void search() throws Exception {
        String param = "title";
        String value = "Вяселка";
        Periodical per = periodicalService.search(param,value);
        LOG.info(per.toString());

         param = "index";
         value = "58745";
         per = periodicalService.search(param,value);
        LOG.info(per.toString());

         param = "price";
         value = "1.47";
         per = periodicalService.search(param,value);
        LOG.info(per.toString());
    }

    @Test
    public void sort(){
        List<Periodical> list = periodicalService.getAll();
        periodicalService.sortByName(list);
        for(Periodical per : list){
            LOG.info(per.getTitle());
        }
    }

    @Test
    public void sortByIndex(){
        List<Periodical> list = periodicalService.getAll();
        periodicalService.sortByIndex(list);
        for(Periodical per : list){
            LOG.info(String.valueOf(per.getIndex()));
        }
    }

}