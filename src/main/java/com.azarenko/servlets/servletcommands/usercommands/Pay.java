package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.Payment;
import com.azarenko.services.PaymentService;
import com.azarenko.exceptions.ServiceException;
import com.azarenko.services.ShoppingCartService;
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
import java.math.BigDecimal;
import java.util.Date;

public class Pay implements Command {
    private final Logger log = Logger.getLogger(Pay.class);
    private final String START = "/user?action=start";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ComponentRegister register = new ComponentRegister();

        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        HttpSession session = request.getSession();
        int userId = 0;
        try {
            transaction.start();
            UserService userService = (UserService) register.getImpl(UserService.class);
            userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
            ShoppingCartService cartService = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
            BigDecimal price = cartService.getFullPriceForPayment(userId);
            if (price == null) {
                transaction.rollback();

                return START;
            }
            Payment payment = getNewPayment(userId, price);
            PaymentService service = (PaymentService) register.getImpl(PaymentService.class);
            service.add(payment);
            transaction.commit();
            cartService.removeShoppingCartUser(userId);
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        } catch (ServiceException e) {
            log.error(e);
        }
        String forward = START;
        return forward;
    }

    private Payment getNewPayment(int userId, BigDecimal price) {
        Payment.PaymentBuilder paymentBuilder = new Payment.PaymentBuilder();
        paymentBuilder.date(new Date());
        paymentBuilder.price(price);
        paymentBuilder.userId(userId);
        return paymentBuilder.build();
    }
}
