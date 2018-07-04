package com.azarenko.web;

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

@WebServlet(name = "/users")
public class UserServlet extends HttpServlet {


    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    private ConfigurableApplicationContext context;
    private UserRestController controller;

    @Override
    public void destroy() {
        super.destroy();
        context.close();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new ClassPathXmlApplicationContext(
                "classpath:spring/spring-app.xml",
                "classpath:spring/spring-db.xml"
        );
        controller=context.getBean(UserRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
