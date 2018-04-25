package com.azarenko.services;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class TransactionImpl extends BaseServiceImpl implements Transaction { private final static Logger log = Logger.getLogger(TransactionImpl.class);
   private ThreadLocal<Connection> local;
   private Connection connection;

    public TransactionImpl(ThreadLocal local) {
        this.local = local;
    }

    @Override
    public void start() throws TransactionException {
        connection =  local.get();
        log.info(connection);
        try {
            connection.setAutoCommit(false);
            setLocal(this.local);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            log.debug(e);
            throw new TransactionException(e);

        }
    }
}
