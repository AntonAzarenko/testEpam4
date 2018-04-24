package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPeriodical;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCatalogPeriodicals implements Command {
    private ArrayOperationPeriodical arrayOperationPeriodical;


    public ShowCatalogPeriodicals(ArrayOperationPeriodical arrayOperationPeriodical) {
        this.arrayOperationPeriodical = arrayOperationPeriodical;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        String f = this.arrayOperationPeriodical.showCatalog(request, resp);
        return f;
    }
}
