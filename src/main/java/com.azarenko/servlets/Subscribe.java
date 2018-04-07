package com.azarenko.servlets;

import com.azarenko.services.PeriodicalsService;
import com.azarenko.services.PeriodicalsServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "subscribe", urlPatterns = "/subscribe")
public class Subscribe extends HttpServlet {
    private final static Logger log = Logger.getLogger(Subscribe.class);

    private PeriodicalsService periodicalsService;

    public Subscribe() {
        periodicalsService = new PeriodicalsServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String forvard = "";

        if (action.equalsIgnoreCase("subscribe")) {
            int id = Integer.parseInt(req.getParameter("periodicalid"));
            req.setAttribute("periodicals", periodicalsService.getPublication(id));
            req.getRequestDispatcher("pages/do_subscribe.jsp").forward(req, resp);
        }else if(action.equalsIgnoreCase("add")){

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
