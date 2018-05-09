package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class RedirectToSubscribe implements Command {
    private final String REDIRCT_TO_SUBSCRIBE = "/pages/user/subscribe.jsp";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        ShowCurrentPeriodical currentPeriodical = new ShowCurrentPeriodical();
        currentPeriodical.execute(request, resp);
        return REDIRCT_TO_SUBSCRIBE;
    }
}
