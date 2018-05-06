package com.azarenko.util;

import com.azarenko.services.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class JdbcTransactionImpl implements Transaction {
    private final static Logger log = Logger.getLogger(JdbcTransactionImpl.class);

    private ConnectionPool connectionPool;

    private Connection connection;

    public JdbcTransactionImpl() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    @Override
    public void start() throws TransactionException {
        connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            JdbcConnectionContext connectionContext = JdbcConnectionContext.getInstance();
            ThreadLocal<Connection> local = new ThreadLocal<>();
            local.set(connection);
            connectionContext.setLocal(local);
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

    @Override
    public void reliaseResources() throws TransactionException, SQLException {
        connection.close();
    }
}
