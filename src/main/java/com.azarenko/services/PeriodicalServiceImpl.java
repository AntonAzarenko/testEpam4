package com.azarenko.services;

import com.azarenko.dao.DaoBase;
import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.Periodicals;

import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {


    DaoBase<Periodicals>daoBase;

    public PeriodicalServiceImpl() {
        daoBase = new PeriodicalsDaoImpl();

    }

    @Override
    public List<Periodicals> getCatalog() {
        return daoBase.getListEntity();
    }

    @Override
    public void add(Periodicals periodicals) {
        daoBase.add(periodicals);
    }

    @Override
    public void remove(int id) {
        daoBase.remove(id);
    }

    @Override
    public void update(Periodicals periodicals) {
        daoBase.update(periodicals);
    }

    @Override
    public Periodicals getPeriodical(int id) {
        return daoBase.getEntityById(id);
    }
}
