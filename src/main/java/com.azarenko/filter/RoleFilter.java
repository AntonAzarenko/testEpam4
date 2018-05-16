package com.azarenko.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {
    private final static Logger log = Logger.getLogger(RoleFilter.class);
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String URI = request.getRequestURI();
        String loginURI = request.getContextPath() + "/authorize";

        boolean temp = session != null && session.getAttribute("login") != null;
        if (temp) {
            if (session.getAttribute("role").equals("USER") && URI.equals("/user")) {
                filterChain.doFilter(request, response);
            } else if (session.getAttribute("role").equals("ADMIN") && URI.equals("/admin")) {
                filterChain.doFilter(request, response);
            } else if (URI.equals("user") || URI.equals("/admin")) {
                session.setAttribute("login", null);
            }
            if (session.getAttribute("login") == null) {
                request.setAttribute("eroor","У вас нет доступа");
            } else {
                filterChain.doFilter(request, response);
            }

        } else {
            log.info("forward");
            log.info(URI);
            request.getRequestDispatcher(loginURI).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
