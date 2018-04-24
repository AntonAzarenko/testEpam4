package com.azarenko.dao;



import org.apache.log4j.Logger;

import java.sql.Connection;

public class BaseDaoImpl {
    private static Logger log = Logger.getLogger(BaseDaoImpl.class);
    private static ThreadLocal<Connection> local;


    public ThreadLocal<Connection> getLocal() {
        return this.local;
    }

    public void setLocal(ThreadLocal<Connection> local) {
        this.local = local;
    }
}
