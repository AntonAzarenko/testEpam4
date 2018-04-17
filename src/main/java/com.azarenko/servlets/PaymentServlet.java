package com.azarenko.servlets;

import com.azarenko.model.Payment;
import com.azarenko.services.PaymentService;
import com.azarenko.services.PaymentServiceImpl;
import com.azarenko.services.UserService;
import com.azarenko.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@WebServlet(name="pay", urlPatterns = "/payment")
public class PaymentServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(PaymentServlet.class);
    private UserService userService;
    private PaymentService paymentService;

    public PaymentServlet() {
        userService = new UserServiceImpl();
        paymentService = new PaymentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = userService.getUserIdByEmail((String) session.getAttribute("login"));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        Payment payment = paymentService.createPayment(userId, price );
        paymentService.add(payment);
        req.getRequestDispatcher("/pages/message.jsp").forward(req,resp);
    }
}
