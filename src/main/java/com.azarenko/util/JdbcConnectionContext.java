package com.azarenko.util;

import java.sql.Connection;

public class JdbcConnectionContext {
    private ThreadLocal<Connection> local;
    private static JdbcConnectionContext connectionContext;

    private JdbcConnectionContext() {
    }

    public static JdbcConnectionContext getInstance(){
        if (connectionContext == null){
            connectionContext = new JdbcConnectionContext();
            return connectionContext;
        }else return connectionContext;
    }

    public ThreadLocal<Connection> getLocal() {
        return local;
    }

    public void setLocal(ThreadLocal<Connection> local) {
        this.local = local;
    }
}
