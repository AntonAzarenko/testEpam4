package com.azarenko.servlets.servletCommands.adminOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ArrayOperationSubscription {
    private final String SUBSCRIPTION = "/pages/admin/subscription.jsp";
    private ConnectionPool pool;

    public ArrayOperationSubscription() {
        pool = ConnectionPool.getInstance();
    }

    public String showSubscription(HttpServletRequest request, HttpServletResponse resp) throws CommandException, TransactionException {
        String forward = "";
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            throw new CommandException(e);
        }
        ThreadLocal<Connection> local = new ThreadLocal<>();
        local.set(connection);
        Transaction transaction = new TransactionImpl(local);
        try {
            transaction.start();
            SubscriptionService service = new SubscriptionServiceImpl();
            request.setAttribute("subscriptionList", service.getAllSubscription());
            transaction.commit();
            pool.returnConnection(connection);
            forward = SUBSCRIPTION;
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
        }

        return forward;
    }
}