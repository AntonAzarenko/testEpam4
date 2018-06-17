package com.azarenko.web.controllers;

import com.azarenko.exceptions.RepositoryExceptions;
import com.azarenko.repository.PeriodicalReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/start")
public class PeriodicalSerrvlet  extends HttpServlet{


    private PeriodicalRestController controller = new PeriodicalRestController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", controller.getListEntity());
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
