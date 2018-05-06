package com.azarenko.dao;

import com.azarenko.model.Subscription;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl extends BaseDaoImpl implements SubscriptionDao {
    private final static Logger log = Logger.getLogger(SubscriptionDaoImpl.class);

    @Override
    public List<Subscription> getAllSubscriptionsUserByUserId(int id) throws DaoException {
        Connection connection = getLocal().get();
        List<Subscription> subscriptionList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.subscriptions WHERE user_id = ?")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()){
                Subscription.SubscriptionBuild subscriptionBuild = new Subscription.SubscriptionBuild();
                subscriptionBuild.id(rs.getInt("id"));
                subscriptionBuild.idPeriodical(rs.getInt("id_periodicals"));
                subscriptionBuild.dateStartSubcription(rs.getDate("date_start"));
                subscriptionBuild.dateEndSubscription(rs.getDate("date_end"));
                subscriptionBuild.namePeriodical(rs.getString("name_periodicals"));
                subscriptionBuild.userId(rs.getInt("user_id"));
                Subscription subscription = subscriptionBuild.build();
                subscriptionList.add(subscription);
            }
        } catch (SQLException e) {
            throw new DaoException(e);

        }
        return subscriptionList;
    }

    @Override
    public Subscription getEntityById(int id) {
        return null;
    }

    @Override
    public void add(Subscription entity) throws DaoException {
        Subscription subscription =  entity;
        Connection connection = getLocal().get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO mydb.subscriptions ( id_periodicals, name_periodicals, date_start, date_end, user_id) VALUES (?,?,?,?,?)")) {
            preparedStatement.setInt(1, subscription.getPeriodicalId());
            preparedStatement.setString(2, subscription.getNamePeriodical());
            preparedStatement.setDate(3, new Date(subscription.getDateStartSubcription().getTime()));
            preparedStatement.setDate(4, new Date(subscription.getDateEndSubscription().getTime()));
            preparedStatement.setInt(5, subscription.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw  new DaoException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Subscription entity) {

    }

    @Override
    public List getListEntity() throws DaoException {
        Connection connection = getLocal().get();
        List<Subscription> subscriptionList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM mydb.subscriptions")) {
            while (rs.next()) {
                Subscription.SubscriptionBuild subscriptionBuild = new Subscription.SubscriptionBuild();
                subscriptionBuild.id(rs.getInt("id"));
                subscriptionBuild.idPeriodical(rs.getInt("id_periodicals"));
                subscriptionBuild.dateStartSubcription(rs.getDate("date_start"));
                subscriptionBuild.dateEndSubscription(rs.getDate("date_end"));
                subscriptionBuild.namePeriodical(rs.getString("name_periodicals"));
                subscriptionBuild.userId(rs.getInt("user_id"));
                Subscription subscription = subscriptionBuild.build();
                subscriptionList.add(subscription);
            }
        } catch (SQLException e) {
           throw  new DaoException(e);
        }
        return subscriptionList;
    }
}
