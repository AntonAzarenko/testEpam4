package com.azarenko.servlets.servletcommands.admincommands;

import com.azarenko.dao.DaoException;
import com.azarenko.services.SubscriptionService;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import com.azarenko.servlets.servletcommands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSubscription implements Command {
    private final String SUBSCRIPTION = "/pages/admin/subscription.jsp";

    @Override
    /**
     *
     */
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, TransactionException {
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            SubscriptionService service = (SubscriptionService) register.getImpl(SubscriptionService.class);
            request.setAttribute("subscriptionList", service.getAllSubscription());
            transaction.commit();
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
        }
        return SUBSCRIPTION;
    }
}
