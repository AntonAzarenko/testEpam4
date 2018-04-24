package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationPayment;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPayment implements Command {
    private ArrayOperationPayment arrayOperationPayment;

    public ShowPayment(ArrayOperationPayment arrayOperationPayment) {
        this.arrayOperationPayment = arrayOperationPayment;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        return this.arrayOperationPayment.showPayments(request, resp);
    }
}
