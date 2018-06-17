package com.azarenko.util;

import com.azarenko.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private final Logger log = Logger.getLogger(ConnectionPool.class);

    private static volatile ConnectionPool pool;

    private int maxPool;

    private String url;


    private String user;

    private String pass;

    private String driver;

    private List<PooledConnection> free, used;

    private ConnectionPool() {
    }

    public static synchronized ConnectionPool getInstance() throws ServiceException {
        if (pool == null) {
            Properties prop = new Properties();
            InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream("db/database.properties");
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            try {
                pool = new ConnectionPool(10, 50, url, user, password, driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pool;
    }

    private ConnectionPool(int minPool, int maxPool, String url, String username, String password, String driver) throws SQLException {
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
        log.info("GetConnection " + cw);
        used.add(cw);

        return cw;
    }


    protected PooledConnection createConnectionWrapper() throws SQLException {
        Connection con = null;
        PooledConnection pcon = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);

            // AddPeriodical caching wrapper to connection.
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
        log.info(con + " Remove Used");
        free.add(con);
        log.info(con + " AddPeriodical Free");

    }

}
