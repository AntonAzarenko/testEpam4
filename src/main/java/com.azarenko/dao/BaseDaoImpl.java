package com.azarenko.dao;


import com.azarenko.util.JdbcConnectionContext;

import java.sql.Connection;

public class BaseDaoImpl {
    private JdbcConnectionContext connectionContext = JdbcConnectionContext.getInstance();
    private ThreadLocal<Connection> local;
    private Connection connection;

    public Connection getConnection() {
        local = connectionContext.getLocal();
        connection = local.get();
        return connection;
    }
}
