package com.azarenko.web;

import com.azarenko.web.controllers.PeriodicalRestController;
import com.azarenko.web.controllers.UserRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserServlet extends HttpServlet {


    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    private ConfigurableApplicationContext context;
    private UserRestController controller;
    private PeriodicalRestController periodicalRestController;

    @Override
    public void destroy() {
        super.destroy();
        context.close();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new ClassPathXmlApplicationContext( new String[]{
                "classpath:spring/spring-app.xml",
                "classpath:spring/spring-db.xml"}, false);
        context.getEnvironment().setActiveProfiles("datajpa");
        context.refresh();
        controller=context.getBean(UserRestController.class);
        periodicalRestController = context.getBean(PeriodicalRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  String email = req.getParameter("email");
        if(email.equalsIgnoreCase("User@ya.ru")){
            req.setAttribute("list", periodicalRestController.getListEntity());
            req.getRequestDispatcher("WEB-INF/jsp/user.jsp").forward(req, resp);
        }
        if(email.equalsIgnoreCase("Admin@gmail.com")){
           resp.sendRedirect("start");
        }
*/

    }
}
