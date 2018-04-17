package com.azarenko.servlets;

import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.Subscription;
import com.azarenko.model.User;
import com.azarenko.services.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(UsersServlet.class);
    private final static String CATALOG_LIST = "/pages/user_catalog.jsp";
    private final static String PROFILE = "/pages/profile.jsp";
    private final static String MY_PERIODICALS = "/pages/my_periodicals.jsp";
    private final static String MY_PROFILE = "/pages/my_profile.jsp";

    private PeriodicalService periodicalService;

    private SubscriptionService subscriptionService;

    private UserService userService;

    public UsersServlet() {
        periodicalService = new PeriodicalServiceImpl();
        userService = new UserServiceImpl();
        subscriptionService = new SubscriptionServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String forward = "";
        if (action.equalsIgnoreCase("catalog")) {
            req.setAttribute("catalogs", periodicalService.getCatalog());
            forward = CATALOG_LIST;
        } else if(action.equalsIgnoreCase("myprofile")){
            forward = MY_PROFILE;
            HttpSession session = req.getSession();
            User user = userService.getUserByEmail(String.valueOf(session.getAttribute("login")));

            req.setAttribute("user", user);

        }else if(action.equalsIgnoreCase("myperiodicals")){
            forward = MY_PERIODICALS;
            HttpSession session = req.getSession();
            int userId = userService.getUserIdByEmail(String.valueOf(session.getAttribute("login")));
            req.setAttribute("subscriptions",subscriptionService.getAllSubscriptionsUserByUserId(userId));
        } else if(action.equalsIgnoreCase("profile")){
            forward = PROFILE;
        }
        req.getRequestDispatcher(forward).forward(req, resp);


    }
}
