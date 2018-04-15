package com.azarenko.servlets;

import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.model.ShoppingCart;
import com.azarenko.model.Subscription;
import com.azarenko.services.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "subscribe", urlPatterns = "/subscribe")
public class Subscribe extends HttpServlet {
    private final static Logger log = Logger.getLogger(Subscribe.class);

    private PeriodicalService periodicalService;
    private UserService userService;
    private ShoppingCartService cartService;

    public Subscribe() {
        periodicalService = new PeriodicalServiceImpl();
        userService = new UsserServiceImpl();
        cartService = new ShoppingCartServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("periodicalid"));
        req.setAttribute("periodicals", periodicalService.getPublication(id));
        req.getRequestDispatcher("pages/do_subscribe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = "";
        HttpSession session = req.getSession();

        int idPeriodical = Integer.parseInt(req.getParameter("id"));
        Date dateStart = getDateToString(req.getParameter("dateStart"));
        Date dateEnd = getDateToString(req.getParameter("dateEnd"));
        String login = String.valueOf(session.getAttribute("login"));
        int userId = userService.getUserIdByEmail(login);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPeriodicalId(idPeriodical);
        shoppingCart.setUserID(userId);
        shoppingCart.setStart(dateStart);
        shoppingCart.setEnd(dateEnd);
        cartService.add(shoppingCart);
        req.getRequestDispatcher("/user").forward(req, resp);
    }

    private Date getDateToString(String text) {
        log.info(text);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(text);
            log.info(date);
        } catch (ParseException e) {
            log.error(e);
        }
        return date;
    }


}
