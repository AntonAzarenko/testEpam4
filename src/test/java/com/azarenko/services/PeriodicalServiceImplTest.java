package com.azarenko.services;


import com.azarenko.model.Periodicals;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

public class PeriodicalServiceImplTest {
    @Test
    public void getCatalog() throws Exception {
    }

    @Test
    public void add() throws Exception {
     Periodicals periodicals = new Periodicals();
     periodicals.setTitle("qwe");
     periodicals.setDescription("qwe");
     periodicals.setPrice(new BigDecimal(1.25));
     periodicals.setOutputFrequency(5);
     ComponentRegister register = new ComponentRegister();
     Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
     transaction.start();
     PeriodicalService service = (PeriodicalService) register.getImpl(PeriodicalService.class);
     service.add(periodicals);
     transaction.commit();
     transaction.reliaseResources();
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void getPeriodical() throws Exception {
    }

    @Test
    public void search() throws Exception {
    }

}