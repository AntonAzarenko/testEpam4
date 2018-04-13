package com.azarenko.servlets;

import com.azarenko.dao.PeriodicalsDao;
import com.azarenko.dao.PeriodicalsDaoImpl;
import com.azarenko.services.PeriodicalService;
import com.azarenko.services.PeriodicalServiceImpl;
import com.azarenko.services.UserService;
import com.azarenko.services.UsserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "subscribe", urlPatterns = "/subscribe")
public class Subscribe extends HttpServlet {
    private final static Logger log = Logger.getLogger(Subscribe.class);

    private PeriodicalService periodicalService;
    private UserService userService;

    public Subscribe() {
        periodicalService = new PeriodicalServiceImpl();
        userService = new UsserServiceImpl();
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
        String namePeriodical = req.getParameter("title");
        Date dateStart = getDateToString(req.getParameter("dateStart"));
        Date dateEnd = getDateToString(req.getParameter("dateEnd"));
        int userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
        log.info(userId);
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
