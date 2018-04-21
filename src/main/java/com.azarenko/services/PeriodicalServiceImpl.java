package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.PeriodicalsImplDao;
import com.azarenko.model.Periodicals;

import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {


    BaseDao<Periodicals> baseDao;

    public PeriodicalServiceImpl() {
        baseDao = new PeriodicalsImplDao();

    }

    @Override
    public List<Periodicals> getCatalog() {
        return baseDao.getListEntity();
    }

    @Override
    public void add(Periodicals periodicals) {
        baseDao.add(periodicals);
    }

    @Override
    public void remove(int id) {
        baseDao.remove(id);
    }

    @Override
    public void update(Periodicals periodicals) {
        baseDao.update(periodicals);
    }

    @Override
    public Periodicals getPeriodical(int id) {
        return baseDao.getEntityById(id);
    }
}
