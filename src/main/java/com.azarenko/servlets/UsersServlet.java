package com.azarenko.servlets;

import com.azarenko.services.*;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import com.azarenko.servlets.servletcommands.UserCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MyServlet", urlPatterns = "/user")
public class UsersServlet extends HttpServlet {

    private final static Logger log = Logger.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new UserCommand();
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
            log.error(e);
            req.setAttribute("error", "Ошибка");
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new UserCommand();
        log.info(req.getRequestURI());
        log.info(req.getParameter("action"));
        String forward = "";
        try {
            forward = command.execute(req, resp);
        } catch (CommandException e) {
            log.error(e);
        } catch (ServiceException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
            req.setAttribute("error", "Ошибка");
        }
        resp.sendRedirect(forward);
    }
}
