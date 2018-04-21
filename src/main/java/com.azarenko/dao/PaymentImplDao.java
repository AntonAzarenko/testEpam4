package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Payment;
import com.azarenko.util.DBUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentImplDao implements PaymentDao {
    private final static Logger log = Logger.getLogger(PaymentImplDao.class);

    private Connection connection;

    public PaymentImplDao() {
        connection = DBUtil.getConnection();
    }

    @Override
    public void add(AbstractBaseEntity entity) {
        Payment payment = (Payment) entity;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT  INTO mydb.payment(date,user_id,price) VALUES (?,?,?)")){
            log.info(payment.getDate());
            preparedStatement.setDate(1, new Date(payment.getDate().getTime()));
            preparedStatement.setInt(2,payment.getUserId());
            preparedStatement.setBigDecimal(3,payment.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(AbstractBaseEntity entity) {

    }

    @Override
    public List getListEntity() {
        List<Payment> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM mydb.payment")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                int userId = rs.getInt("user_id");
                BigDecimal price = rs.getBigDecimal("price");
                Payment.PaymentBuilder paymentBuilder = new Payment.PaymentBuilder();
                paymentBuilder.id(id);
                paymentBuilder.date(date);
                paymentBuilder.userId(userId);
                paymentBuilder.price(price);
                Payment payment = paymentBuilder.build();
                list.add(payment);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return list;
    }


    @Override
    public Payment createPayment(int userId, BigDecimal price) {
        return null;
    }

    @Override
    public AbstractBaseEntity getEntityById(int id) {
        return null;
    }
}
