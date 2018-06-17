package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.DaoException;
import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.Periodical;
import com.azarenko.repository.PeriodicalReposiroty;

import com.azarenko.util.ComponentRegister;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PeriodicalServiceImpl implements PeriodicalService {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalServiceImpl.class);

    @Autowired
    PeriodicalReposiroty periodicalReposiroty;

    @Override
    public List<Periodical> getCatalog()throws DaoException {
        /*BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();*/

      return null;
    }

    @Override
    public void add(Periodical periodical) throws DaoException {
        /*BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        baseDao.add(periodical);*/
        Periodical per = periodicalReposiroty.add(periodical, 1);
        if(per != null){
            LOG.info("add : status - ok");
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        baseDao.remove(id);
    }

    @Override
    public void update(Periodical periodical) throws DaoException {
        BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        baseDao.update(periodical);
    }

    @Override
    public Periodical getPeriodical(int id) throws DaoException {
        BaseDao<Periodical> baseDao = new PeriodicalsDaoImpl();
        return baseDao.getEntityById(id);
    }

    @Override
    public Periodical search(String param, String value) throws ServiceException, DaoException {
        ComponentRegister register = new ComponentRegister();
        PeriodicalsDao dao = (PeriodicalsDao) register.getImpl(PeriodicalsDao.class);
        Periodical periodical = null;
        switch (param) {
            case "name":
                periodical = dao.search(value);

                break;
            case "id":
                int id = checkToValidInt(value);
                periodical = dao.search(id);
                break;
            case "price":
                BigDecimal price = checkToValidBD(value);
                if (price == null) {
                    return null;
                }
                periodical = dao.search(price);
                break;
        }
        return periodical;
    }

    private BigDecimal checkToValidBD(String value) throws ServiceException {
        BigDecimal price;
        try{
             price = BigDecimal.valueOf(Double.parseDouble(value));
        }catch (NumberFormatException e){

            throw new ServiceException(e);
        }
        return price;
    }

    private int checkToValidInt(String value) throws ServiceException {
        int id;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {

            throw new ServiceException(e);
        }
        return id;
    }
}



