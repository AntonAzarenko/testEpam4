package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.Periodicals;

import java.util.List;

public class PeriodicalServiceImpl extends BaseService implements PeriodicalService {

   private BaseDao<Periodicals> baseDao;

    public PeriodicalServiceImpl() {
        baseDao = new PeriodicalsDaoImpl();

    }

    @Override
    public List<Periodicals> getCatalog() throws ServiceException, DaoException {
        return baseDao.getListEntity();
    }

    @Override
    public void add(Periodicals periodicals) throws DaoException {
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
