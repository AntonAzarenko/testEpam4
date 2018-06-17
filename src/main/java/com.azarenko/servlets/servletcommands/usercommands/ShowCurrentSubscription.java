package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Subscription;
import com.azarenko.exceptions.ServiceException;
import com.azarenko.services.SubscriptionService;
import com.azarenko.services.UserService;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.util.ComponentRegister;
import com.azarenko.util.JdbcTransactionImpl;
import com.azarenko.util.Transaction;
import com.azarenko.util.TransactionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

public class ShowCurrentSubscription implements Command {
    private final Logger log = Logger.getLogger(ShowCurrentSubscription.class);
    private final String CURRENT_SUBSCRIPTION = "/pages/user/currentsubscription.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        String id = request.getParameter("subId");
        HttpSession session = request.getSession();
        int idSubscription = 0;

        try {
            idSubscription = Integer.parseInt(id);
            transaction.start();
            SubscriptionService service = (SubscriptionService) register.getImpl(SubscriptionService.class);
            UserService userService = (UserService) register.getImpl(UserService.class);
            int userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
            List<Subscription> subscriptionList = service.getAllSubscriptionsUserByUserId(userId);
            Subscription subscription = service.getSubscriptionById(idSubscription);
            request.setAttribute("subscriptionL", subscription);
            request.setAttribute("subList", subscriptionList);
            transaction.commit();
        } catch (TransactionException e) {
            log.error(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error", "Транзакция закончилась неудачей");
        } catch (NumberFormatException e) {
            log.error(e);
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return CURRENT_SUBSCRIPTION;
    }
}
