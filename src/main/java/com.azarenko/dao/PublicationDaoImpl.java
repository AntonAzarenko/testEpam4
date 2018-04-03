package com.azarenko.dao;

import com.azarenko.model.Publication;
import com.azarenko.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoImpl implements PublicationDao {

    private Connection connection;

    public PublicationDaoImpl() {
       connection = DBUtil.getConnection();
    }

    public List<Publication> getCatalog() {
        List<Publication> temp = new ArrayList<Publication>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM mydb.catalogpublications");
            while (rs.next()) {
                int id = rs.getInt("idpublications");
                String title = rs.getString("title");
                String discription = rs.getString("discription");
                BigDecimal price =rs.getBigDecimal("price");
                Publication publication = new Publication(id, title, discription, price);
                temp.add(publication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public void add(Publication publication) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO  catalogpublications(title,discription, price) values (?,?,?)");
            preparedStatement.setString(1, publication.getTitle());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setBigDecimal(3, publication.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Publication publication) {

    }

    @Override
    public Publication getPublication(int id) {
        return null;
    }
}
