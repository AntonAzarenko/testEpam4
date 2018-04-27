package com.azarenko.servlets.servletCommands.userCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ShowCurrentPeriodical implements Command{
    private ArrayOperationPeriodical periodical;

    public ShowCurrentPeriodical(ArrayOperationPeriodical periodical) {
        this.periodical = periodical;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.periodical.showCurrentPeriodical(request, resp);
    }
}
