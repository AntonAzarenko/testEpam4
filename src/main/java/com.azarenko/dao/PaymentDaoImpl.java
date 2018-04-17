package com.azarenko.dao;

import com.azarenko.model.AbstractBaseEntity;
import com.azarenko.model.Payment;
import com.azarenko.util.DBUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    private final static Logger log = Logger.getLogger(PaymentDaoImpl.class);

    private Connection connection;

    public PaymentDaoImpl() {
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
        return null;
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
