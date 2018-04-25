package com.azarenko.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/auth")
public class AuthFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthFilter.class);
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
        String loginURI = request.getContextPath() + "/authorize";
        boolean loggedIn = session != null && session.getAttribute("login") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        String uri = request.getRequestURI();
        log.info(uri);
        if(uri.equals("/registration")){
            response.sendRedirect(loginURI);
        }
        if(uri.equals("/css/start.css")){
            filterChain.doFilter(request,response);
        }
        if (loggedIn || loginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}
