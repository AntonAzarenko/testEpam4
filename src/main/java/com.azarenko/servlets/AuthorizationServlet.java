package com.azarenko.servlets;

import com.azarenko.services.ServiceException;
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

@WebServlet(name = "authorize", urlPatterns = "/authorize")
public class AuthorizationServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(AuthorizationServlet.class);
    private final static String START = "/pages/start.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(START).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new CommandImpl();
        String forward = "";
        try {
            log.info(req.getParameter("action"));
            forward = command.execute(req,resp);
        } catch (CommandException e) {
            log.error(e);
        } catch (ServiceException e) {
            log.error(e);
        }
        req.getRequestDispatcher(forward).forward(req, resp);
    }
}
