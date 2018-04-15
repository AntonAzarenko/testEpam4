package com.azarenko.servlets;

import com.azarenko.services.ShoppingCartService;
import com.azarenko.services.ShoppingCartServiceImpl;
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

@WebServlet(name = "cart", urlPatterns = "/cart")
public class ShoppingCartServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(ShoppingCartServlet.class);

    private ShoppingCartService cartService;
    private UserService userService;

    public ShoppingCartServlet() {
        cartService = new ShoppingCartServiceImpl();
        userService = new UsserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login  = String.valueOf(session.getAttribute("login"));
        int userId = userService.getUserIdByEmail(login);

        req.setAttribute("cartList", cartService.getShoppingCartUser(userId));
        req.getRequestDispatcher("/pages/shopping_cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login  = String.valueOf(session.getAttribute("login"));
        int userId = userService.getUserIdByEmail(login);

        req.setAttribute("cartList", cartService.getShoppingCartUser(userId));
        req.getRequestDispatcher("/shoppingCart.jsp").forward(req,resp);
        }
}
