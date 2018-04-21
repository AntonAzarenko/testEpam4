package com.azarenko.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
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
            File file = new File("/java/database.properties");
            InputStream inputStream = new FileInputStream(file);
            String path = file.getPath();
            System.out.println(path);

         //   prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("name");
            String password = prop.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user ,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
       } catch (IOException e) {
            e.printStackTrace();
        }

            return connection;

    }
}
