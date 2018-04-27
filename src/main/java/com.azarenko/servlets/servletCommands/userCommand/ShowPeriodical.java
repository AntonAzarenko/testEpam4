package com.azarenko.servlets.servletCommands.userCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationSubscription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowPeriodical implements Command {
    private ArrayOperationSubscription operationSubscription;

    public ShowPeriodical(ArrayOperationSubscription operationSubscription) {
        this.operationSubscription = operationSubscription;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.operationSubscription.showPeriodicals(request, resp);
    }
}
