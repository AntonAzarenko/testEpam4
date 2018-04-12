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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "subscribe", urlPatterns = "/subscribe")
public class Subscribe extends HttpServlet {
    private final static Logger log = Logger.getLogger(Subscribe.class);

    private PeriodicalsService periodicalsService;

    public Subscribe() {
        periodicalsService = new PeriodicalsServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("periodicalid"));
        req.setAttribute("periodicals", periodicalsService.getPublication(id));
        req.getRequestDispatcher("pages/do_subscribe.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = "";


        int idPeriodical = Integer.parseInt(req.getParameter("id"));
        String namePeriodical = req.getParameter("title");
        Date dateStart = getDateToString(req.getParameter("dateStart"));
        Date dateEnd = getDateToString(req.getParameter("dateEnd"));
        int userId = Integer.parseInt(req.getParameter(""));


    }

    private Date getDateToString(String text) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        Date date = new Date();
        try {
            date = format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
