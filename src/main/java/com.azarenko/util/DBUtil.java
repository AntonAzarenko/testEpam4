package com.azarenko.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static Connection connection;

    public static Connection getConnection()  {
        if(connection != null){
            return connection;
        }
        try {
            Properties prop = new Properties();
          /*  InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream(
                    "/resources/database.properties");
            prop.load(inputStream);
            String driver = prop.getProperty("database.driverClass");
            String url = prop.getProperty("database.url");
            String user = prop.getProperty("database.username");
            String password = prop.getProperty("database.password");
           */ Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
