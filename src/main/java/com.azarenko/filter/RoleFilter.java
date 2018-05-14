package com.azarenko.filter;

import com.azarenko.Main;
import com.azarenko.util.config.Application;
import com.azarenko.util.config.RoleLoader;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
        RoleLoader loder  = RoleLoader.getInstance();
        List<String> adminPages = loder.getAdminPages();
        List<String> userPages = loder.getUserPages();

        for(String pair : adminPages){
            System.out.println(pair);
        }
        for(String pair : userPages){
            System.out.println(pair);
        }
        log.info(request.getRequestURI());
        log.info(request.getAuthType());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
