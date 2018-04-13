package com.azarenko.services;

import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.Periodicals;

import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {

    private PeriodicalsDao periodicalsDao;

    public PeriodicalServiceImpl() {
        periodicalsDao = new PeriodicalsDaoImpl();
    }

    @Override
    public List<Periodicals> getCatalog() {
        return periodicalsDao.getCatalog();
    }

    @Override
    public void add(Periodicals periodicals) {
        periodicalsDao.add(periodicals);
    }

    @Override
    public void remove(int id) {
        periodicalsDao.remove(id);
    }

    @Override
    public void update(Periodicals periodicals) {
        periodicalsDao.update(periodicals);
    }

    @Override
    public Periodicals getPublication(int id) {
        return periodicalsDao.getPublication(id);
    }
}
