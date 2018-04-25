package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationRegistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authorization implements Command {
    private ArrayOperationRegistration arrayOperationRegistration;

    public Authorization(ArrayOperationRegistration arrayOperationRegistration) {
        this.arrayOperationRegistration = arrayOperationRegistration;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        return this.arrayOperationRegistration.authorization(request,resp);
    }
}
