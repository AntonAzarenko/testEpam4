package com.azarenko.servlets.servletcommands.usercommands;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletcommands.Command;
import com.azarenko.servlets.servletcommands.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowCurrentSubscription implements Command{
    private final Logger log = Logger.getLogger(ShowCurrentSubscription.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws   CommandException, ServiceException, UnsupportedEncodingException {
        log.info(request.getParameter("periodicalId"));
        return "/user?action=profile";
    }

}
