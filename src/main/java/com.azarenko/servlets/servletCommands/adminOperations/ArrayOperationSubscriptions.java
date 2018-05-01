package com.azarenko.servlets.servletCommands.adminOperations;

import com.azarenko.dao.DaoException;
import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ArrayOperationSubscriptions {
    private final String SUBSCRIPTION = "/pages/admin/subscription.jsp";
    private ConnectionPool pool;

    public ArrayOperationSubscriptions() {
        try {
            pool = ConnectionPool.getInstance();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method implements access to data base with help to some method. Then  get Arraylist with objects and install
     * them to parameter "request".
     *
     * @param request
     * @param resp
     * @return
     * @throws CommandException
     * @throws TransactionException
     */
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
            ComponentRegister register = new ComponentRegister();
            SubscriptionService service = (SubscriptionService) register.getImpl(SubscriptionService.class);
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
