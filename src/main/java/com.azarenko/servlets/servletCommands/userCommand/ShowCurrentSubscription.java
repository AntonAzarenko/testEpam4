package com.azarenko.servlets.servletCommands.userCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationSubscription;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowCurrentSubscription implements Command{
    private final Logger log = Logger.getLogger(ShowCurrentSubscription.class);
    private ArrayOperationSubscription operationSubscription;

    public ShowCurrentSubscription(ArrayOperationSubscription operationSubscription) {
        this.operationSubscription = operationSubscription;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        log.info(request.getParameter("periodicalId"));
        return "/user?action=profile";
    }

}
