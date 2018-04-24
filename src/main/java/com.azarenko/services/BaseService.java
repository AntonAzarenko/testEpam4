package com.azarenko.services;

import com.azarenko.dao.BaseDao;
import com.azarenko.dao.BaseDaoImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class BaseService {
    private final static Logger log = Logger.getLogger(BaseService.class);

    private ThreadLocal<Connection> local;

    public ThreadLocal getLocal() {
        return local;
    }

    public void setLocal(ThreadLocal local) {
        this.local = local;
        BaseDaoImpl baseDao = new BaseDaoImpl();
        baseDao.setLocal(this.local);
    }
}
