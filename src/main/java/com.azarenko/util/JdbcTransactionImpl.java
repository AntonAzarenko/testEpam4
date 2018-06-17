package com.azarenko.util;

import com.azarenko.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class JdbcTransactionImpl implements Transaction {
    private final static Logger log = Logger.getLogger(JdbcTransactionImpl.class);

    private ConnectionPool connectionPool;

    private JdbcConnectionContext connectionContext;

    public JdbcTransactionImpl() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            log.error(e);
        }
    }

    @Override
    public void start() throws TransactionException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            connectionContext = JdbcConnectionContext.getInstance();
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
            connectionContext = JdbcConnectionContext.getInstance();
            Connection connection = connectionContext.getLocal().get();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connectionContext = JdbcConnectionContext.getInstance();
            Connection connection = connectionContext.getLocal().get();
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.debug(e);
            throw new TransactionException(e);
        }
    }

    @Override
    public void reliaseResources() throws TransactionException, SQLException {
        connectionContext = JdbcConnectionContext.getInstance();
        Connection connection = connectionContext.getLocal().get();
        connection.close();
    }
}
