package com.azarenko.servlets;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.servlets.servletcommands.RegistrationCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "authorize", urlPatterns = "/authorize")
public class AuthorizationServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(AuthorizationServlet.class);
    private final String START = "pages/authorize.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
       /* req.getRequestDispatcher(START).forward(req, resp);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new RegistrationCommand();
        String forwardPage = "";
        try {
            log.info(req.getParameter("action"));
            forwardPage = command.execute(req, resp);
        } catch (CommandException e) {
            log.error(e);
            req.setAttribute("error", "Ошибка");
        } catch (ServiceException e) {
            log.error(e);
            req.setAttribute("error", "Ошибка");
        } catch (SQLException e) {
            log.error(e);
            req.setAttribute("error", "Ошибка");
        }
        req.getRequestDispatcher(forwardPage).forward(req, resp);
    }
}
