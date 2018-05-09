package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.dao.DaoException;
import com.azarenko.model.ShoppingCart;
import com.azarenko.services.ServiceException;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddToShoppingCart implements Command {

    private final Logger log = Logger.getLogger(AddToShoppingCart.class);
    private final String CATALOG_LIST = "/user?action=start";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        //get UserPage Id from data base with help login
        int userId = 0;
        HttpSession session = request.getSession();
        ComponentRegister register = new ComponentRegister();
        Transaction transaction = (Transaction) register.getImpl(JdbcTransactionImpl.class);
        try {
            transaction.start();
            UserService service = (UserService) register.getImpl(UserService.class);
            userId = service.getUserIdByEmail((String) session.getAttribute("login"));
        } catch (TransactionException e) {
            throw new TransactionException(e);
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
        }
        int periodicalId = 0;
        try {
            periodicalId = Integer.parseInt(request.getParameter("periodicalId"));
        } catch (NumberFormatException e) {
            log.error(e);
        }
        String count = request.getParameter("count");
        int countPer = 0;
        try {
            countPer = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            log.error(e);
        }
     /* //TODO
           String sDate = request.getParameter("dateStart");
        if (sDate.isEmpty() || sDate == null){
            shoppingCart.setStart(new Date());
        }else {
            shoppingCart.setStart(getDateToString(sDate));
        }*/

        shoppingCart.setUserID(userId);
        shoppingCart.setPeriodicalId(periodicalId);
        shoppingCart.setCountPer(countPer);
        ShoppingCartService service = (ShoppingCartService) register.getImpl(ShoppingCartService.class);
        try {
            service.add(shoppingCart);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error"," error");
        }
        finally {
            try {
                transaction.reliaseResources();
            } catch (SQLException e) {
                log.error(e);
            }
        }

        return CATALOG_LIST;
    }

    private Date getDateToString(String text) {
        log.info(text);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(text);
        } catch (ParseException e) {
            log.error(e);
        }
        return date;
    }
}
