package com.azarenko.servlets;

import com.azarenko.services.*;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.CommandImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(UsersServlet.class);
    private final static String CATALOG_LIST = "/pages/user/user_catalog.jsp";
    private final static String PROFILE = "/pages/user/profile.jsp";
    private final static String MY_PERIODICALS = "/pages/user/my_periodicals.jsp";
    private final static String MY_PROFILE = "/pages/user/my_profile.jsp";

    private PeriodicalService periodicalService;

    private SubscriptionService subscriptionService;

    private UserService userService;

    public UsersServlet() {
        periodicalService = new PeriodicalServiceImplImpl();
        userService = new UserServiceImpl();
        subscriptionService = new SubscriptionServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new CommandImpl();
        log.info(req.getRequestURI());
        log.info(req.getParameter("action"));
        String forward = "";
        try {
            forward = command.execute(req, resp);
        } catch (CommandException e) {
            log.error(e);
        } catch (ServiceException e) {
            log.error(e);
        }
        req.getRequestDispatcher(forward).forward(req, resp);

      /*  String action = req.getParameter("action");
        String forward = "";
        if (action.equalsIgnoreCase("catalog")) {
          //  req.setAttribute("catalogs", periodicalService.getCatalog());
            forward = CATALOG_LIST;
        } else if(action.equalsIgnoreCase("myprofile")){
            forward = MY_PROFILE;
            HttpSession session = req.getSession();
        //    User user = userService.getUserByEmail(String.valueOf(session.getAttribute("login")));

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
*/

    }
}
