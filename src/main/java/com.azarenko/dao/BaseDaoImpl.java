package com.azarenko.dao;


import java.sql.Connection;

public abstract class BaseDaoImpl  {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
