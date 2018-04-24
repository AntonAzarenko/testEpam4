package com.azarenko.servlets.servletCommands.adminOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArrayOperationRegistration {
    private final String REGISTERED = "/pages/user/registration.jsp";

    public String registration(HttpServletRequest request, HttpServletResponse resp) {
        String forward = REGISTERED;
        return forward;
    }
}
