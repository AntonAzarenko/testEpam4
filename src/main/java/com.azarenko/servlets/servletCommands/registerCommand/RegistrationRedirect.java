package com.azarenko.servlets.servletCommands.registerCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.registerOperation.ArrayOperationRegistration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class RegistrationRedirect implements Command {
    private ArrayOperationRegistration operationAuthorization;

    public RegistrationRedirect(ArrayOperationRegistration operationAuthorization) {
        this.operationAuthorization = operationAuthorization;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.operationAuthorization.registrationRedirect(request, resp);
    }
}
