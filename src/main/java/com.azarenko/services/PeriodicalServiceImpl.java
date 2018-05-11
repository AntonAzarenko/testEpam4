package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.Periodicals;
import com.azarenko.util.ComponentRegister;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {
    private final static Logger log = Logger.getLogger(PeriodicalServiceImpl.class);

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
    public Periodicals search(String param, String value) throws ServiceException, DaoException {
        ComponentRegister register = new ComponentRegister();
        PeriodicalsDao dao = (PeriodicalsDao) register.getImpl(PeriodicalsDao.class);
        Periodicals periodical = null;
        if (param.equals("name")) {
           periodical = dao.search(value);
           log.info(value);
        } else if (param.equals("id")) {
            int id = checkToValidInt(value);
            periodical = dao.search(id);
        } else if (param.equals("price")) {
            BigDecimal price = checkToValidBD(value);
            if(price == null){
                return periodical;
            }
            periodical = dao.search(price);
        }
        return periodical;
    }

    private BigDecimal checkToValidBD(String value) throws ServiceException {
        BigDecimal price = null;
        try{
             price = BigDecimal.valueOf(Double.parseDouble(value));
        }catch (NumberFormatException e){
            log.error(e);
            throw new ServiceException(e);
        }
        return price;
    }

    private int checkToValidInt(String value) throws ServiceException {
        int id = 0;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return id;
    }
}



