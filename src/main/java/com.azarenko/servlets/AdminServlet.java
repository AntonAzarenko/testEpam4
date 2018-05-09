package com.azarenko.servlets;

import com.azarenko.services.*;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.AdminCommand;
import com.azarenko.servlets.servletcommands.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "admin", urlPatterns = "/admin", loadOnStartup = 0)
public class AdminServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new AdminCommand();
        String forward = "";
        try {
            try {
                forward = command.execute(req, resp);
            } catch (SQLException e) {
                log.error(e);
                req.setAttribute("error", "Ошибка");
            }
        } catch (CommandException e) {
            req.setAttribute("error", "Ошибка");
            log.error(e);
        } catch (ServiceException e) {
            req.setAttribute("error", "Ошибка");
            log.error(e);
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Command command = new AdminCommand();
        log.info(req.getRequestURI());
        log.info(req.getParameter("action"));
        String forward = "";
        try {
            forward = command.execute(req, resp);
        } catch (CommandException e) {
            log.error(e);
            req.setAttribute("error", "Ошибка");
        } catch (ServiceException e) {
            log.error(e);
            req.setAttribute("error", "Ошибка");
        } catch (SQLException e) {
            req.setAttribute("error", "Ошибка");
            log.error(e);
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }
}
