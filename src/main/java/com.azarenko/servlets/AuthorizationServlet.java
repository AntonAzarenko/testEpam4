package com.azarenko.servlets;

import com.azarenko.services.AuthorisationService;
import com.azarenko.services.ServiceException;
import com.azarenko.services.UserService;
import com.azarenko.services.UserServiceImpl;
import com.azarenko.servlets.adminServletCommand.Command;
import com.azarenko.servlets.adminServletCommand.CommandException;
import com.azarenko.servlets.adminServletCommand.CommandImpl;
import com.azarenko.util.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "authorize", urlPatterns = "/authorize")
public class AuthorizationServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(AuthorizationServlet.class);
    private final static String ERROR = "/pages/info/erors_message.jsp";
    private final static String ADMIN = "pages/admin/admin_start_page.jsp";
    private final static String USER = "user?action=catalog";
    private final static String START = "/pages/start.jsp";
    private final static String REGISTERED = "/pages/user/registration.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(START).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new CommandImpl();
        String forward = null;
        try {
            forward = command.execute(req,resp);
        } catch (CommandException e) {
            log.error(e);
        } catch (ServiceException e) {
            log.error(e);
        }
  /*      Connection connection = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        AuthorisationService authorized = new AuthorisationService();
              String action = req.getParameter("action");
        String forward = "";
        HttpSession session = req.getSession();
        if (action.equalsIgnoreCase("authorize")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (login == null || password == null) {
                forward = ERROR;
                req.getRequestDispatcher(forward).forward(req, resp);
            }

            String role = authorized.authorizeUser(login, password);
            if (role == null) {
                req.getRequestDispatcher(ERROR).forward(req, resp);
            }
            switch (role) {
                case "ADMIN":
                    forward = ADMIN;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    req.getRequestDispatcher(forward).forward(req, resp);
                    break;
                case "USER":
                    forward = USER;
                    session.setAttribute("role", role);
                    session.setAttribute("login", login);
                    req.getRequestDispatcher(forward).forward(req, resp);
                    break;
                default:
                    forward = ERROR;
            }
        } else if (action.equalsIgnoreCase("register")) {
            forward = REGISTERED;
            session.setAttribute("login", "login");
        }*/
        req.getRequestDispatcher(forward).forward(req, resp);
    }
}
