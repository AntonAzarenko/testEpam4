package com.azarenko.servlets.servletCommands.userOperations;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArrayOperationNews {
    private final Logger log = Logger.getLogger(ArrayOperationNews.class);
    private final String NEWS = "/pages/user/news.jsp";
    public String showNews(HttpServletRequest request, HttpServletResponse resp) {
        return NEWS;
    }
}
