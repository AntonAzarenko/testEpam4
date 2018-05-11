package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.Periodicals;
import com.azarenko.util.ComponentRegister;

import java.util.List;

public class PeriodicalServiceImpl  implements PeriodicalService {

    @Override
    public List<Periodicals> getCatalog() throws ServiceException, DaoException {
        BaseDao<Periodicals> baseDao = new PeriodicalsDaoImpl();
        return baseDao.getListEntity();
    }

    @Override
    public void add(Periodicals periodicals) throws DaoException {
        BaseDao<Periodicals> baseDao = new PeriodicalsDaoImpl();
        baseDao.add(periodicals);
    }

    @Override
    public void remove(int id) throws DaoException {
        BaseDao<Periodicals> baseDao = new PeriodicalsDaoImpl();
        baseDao.remove(id);
    }

    @Override
    public void update(Periodicals periodicals) throws DaoException {
        BaseDao<Periodicals> baseDao = new PeriodicalsDaoImpl();
        baseDao.update(periodicals);
    }

    @Override
    public Periodicals getPeriodical(int id) throws DaoException {
        BaseDao<Periodicals> baseDao = new PeriodicalsDaoImpl();
        return baseDao.getEntityById(id);
    }

    @Override
    public Periodicals search(String param) {
        ComponentRegister register = new ComponentRegister();
        PeriodicalsDao dao = (PeriodicalsDao) register.getImpl(PeriodicalsDao.class);
        return dao.search(param);
    }
}
