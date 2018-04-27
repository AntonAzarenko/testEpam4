package com.azarenko.servlets.servletCommands.userCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowProfile implements Command {
    private ArrayOperationUser operationUser;

    public ShowProfile(ArrayOperationUser operationUser) {
        this.operationUser = operationUser;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.operationUser.getUser(request,resp);
    }
}
