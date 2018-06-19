package com.azarenko.web.controllers;

import com.azarenko.model.Periodical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/start")
public class PeriodicalServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(PeriodicalServlet.class);
    private PeriodicalRestController controller;
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-app.xml",
                "classpath:spring/spring-db.xml");
        controller = applicationContext.getBean(PeriodicalRestController.class);
    }

    @Override
    public void destroy() {
        applicationContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            req.setAttribute("list", controller.getListEntity());
            req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);
        }
        if ("add".equals(action)) {
            req.getRequestDispatcher("WEB-INF/jsp/add_edit_publications.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            req.setAttribute("periodical", controller.get(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("WEB-INF/jsp/add_edit_publications.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            controller.remove(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("list", controller.getListEntity());
            req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String discription = req.getParameter("discription");
        int index = Integer.parseInt(req.getParameter("index"));
        int opFr = Integer.parseInt(req.getParameter("of"));
        int ageLimit = Integer.parseInt(req.getParameter("al"));
        String publisher = req.getParameter("pub");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Periodical periodical = new Periodical(id,title, discription, publisher, opFr, index ,ageLimit, price);
        LOG.info(periodical.toString());
        controller.add(periodical);
        req.setAttribute("list", controller.getListEntity());
        req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);
    }
}
