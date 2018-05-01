package com.azarenko.servlets.servletCommands.userCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RedirectToSubscribe implements Command {
    private ArrayOperationPeriodical operationPeriodical;
    private final String REDIRCT_TO_SUBSCRIBE  ="/pages/user/subscribe.jsp";

    public RedirectToSubscribe(ArrayOperationPeriodical operationPeriodical) {
        this.operationPeriodical = operationPeriodical;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        this.operationPeriodical.showCurrentPeriodical(request, resp);
        return REDIRCT_TO_SUBSCRIBE;
    }
}
