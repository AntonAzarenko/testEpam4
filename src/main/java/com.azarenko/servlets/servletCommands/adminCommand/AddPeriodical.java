package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class AddPeriodical implements Command {
    private ArrayOperationPeriodical arrayOperationPeriodical;

    public AddPeriodical(ArrayOperationPeriodical arrayOperationPeriodical) {
        this.arrayOperationPeriodical = arrayOperationPeriodical;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.arrayOperationPeriodical.add(request,resp);
    }
}
