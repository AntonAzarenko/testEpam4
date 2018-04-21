package com.azarenko.util;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionPool {

    private final Logger log = Logger.getLogger(ConnectionPool.class);

    private static volatile ConnectionPool pool;

    private int maxPool;

    /**
     * JDBC URL to use for connecting to the database server.
     */
    private String url;

    /**
     * Username to use for connecting to the database server.
     */
    private String user;

    /**
     * Password to use for connecting to the database server.
     */
    private String pass;

    private String driver;

    private List<PooledConnection> free, used;

    private ConnectionPool() {
    }
    public static synchronized ConnectionPool getInstance
            (int minPool, int maxPool, String url, String username, String password, String driver) throws SQLException {
        if(pool == null){
           pool = new ConnectionPool(minPool,maxPool,url,username,password,driver);
        }
        return pool;
    }

    private   ConnectionPool(int minPool, int maxPool, String url, String username, String password, String driver) throws SQLException {
        this.maxPool = maxPool;
        this.url = url;
        this.user = username;
        this.pass = password;
        this.driver = driver;

        free = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));
        used = Collections.synchronizedList(new ArrayList<PooledConnection>(maxPool));

        for (int i = 0; i < minPool; i++) {
            free.add(createConnectionWrapper());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

    public synchronized void destroy() {
        for (PooledConnection cw : free) {
            try {
                cw.getRawConnection().close();
            } catch (Exception e) {
                log.info("Unable to close connection");
            }
        }
        for (PooledConnection cw : used) {
            try {
                cw.getRawConnection().close();
            } catch (Exception e) {
                log.info("Unable to close connection");
            }
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        PooledConnection cw = null;

        if (free.size() > 0) {
            cw = free.remove(free.size() - 1);
        } else if (used.size() < maxPool) {
            cw = createConnectionWrapper();
        } else {
            throw new RuntimeException("Unable to create a connection");
        }

        used.add(cw);

        return cw;
    }

    protected PooledConnection createConnectionWrapper() throws SQLException {
        Connection con = null;
        PooledConnection pcon = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);

            // Add caching wrapper to connection.
            pcon = new PooledConnection(this, con);
            log.info("Created a new connection");

            // Check for warnings.
            SQLWarning warn = con.getWarnings();

            while (warn != null) {
                log.info("Warning - {}" + warn.getMessage());
                warn = warn.getNextWarning();
            }
        } catch (SQLException ex) {
            log.error("Can't create a new connection for {}" + url, ex);
            // Clean up open connection.
            try {
                if (con != null) con.close();
            } catch (SQLException ex2) {
                log.warn("Unable to close connection", ex2);
            }
            // Rethrow exception.
            throw ex;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return pcon;
    }

    protected void freeConnectionWrapper(PooledConnection con) {
        used.remove(con);
        free.add(con);
    }

}
