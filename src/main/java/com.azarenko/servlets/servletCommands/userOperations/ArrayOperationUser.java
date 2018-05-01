package com.azarenko.servlets.servletCommands.userOperations;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArrayOperationUser {
    private final Logger log =  Logger.getLogger(ArrayOperationUser.class);

    private final String START ="/pages/start_user.jsp";

    public String getUser(HttpServletRequest request, HttpServletResponse resp) {
        String forward ="";
        return forward;
    }

    public String userExit(HttpServletRequest request, HttpServletResponse resp) {
        return "";
    }

    public String forwardStart(HttpServletRequest request, HttpServletResponse resp) {
        return START;
    }

    public String profile(HttpServletRequest request, HttpServletResponse resp) {
        return "/pages/user/profile.jsp";
    }
}
