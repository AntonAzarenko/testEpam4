package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.ShoppingCart;
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
import java.sql.SQLException;
import java.util.List;

public class ShowShoppingCart implements Command {
    private final Logger log = Logger.getLogger(ShowShoppingCart.class);
    private final String SHOPPING_CART = "/pages/user/shopping_cart.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            ShoppingCartService serviceCart = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
            HttpSession session = request.getSession();
            UserService userService = (UserService) register.getImpl(UserService.class);
            int userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
            List<ShoppingCart> shoppingCarts = serviceCart.getShoppingCartUser(userId);
            request.setAttribute("cartList", shoppingCarts);
            BigDecimal fullPrice = serviceCart.getFullPriceForPayment(userId);
            request.setAttribute("FP", fullPrice);
            transaction.commit();

        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error", "error");
        } finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return SHOPPING_CART;
    }
}
