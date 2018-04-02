package com.azarenko.dao;

import com.azarenko.model.Catalog;
import com.azarenko.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogDaoImpl implements CatalogDao {

    private Connection connection;

    public CatalogDaoImpl() {
       connection = DBUtil.getConnection();
    }

    public List<Catalog> getCatalog() {
        List<Catalog> temp = new ArrayList<Catalog>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalogpublications");
            while (rs.next()) {
                int id = rs.getInt("idpublications");
                String title = rs.getString("title");
                String discription = rs.getString("discription");
                BigDecimal price =rs.getBigDecimal("price");
                Catalog catalog = new Catalog(id, title, discription, price);
                temp.add(catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
