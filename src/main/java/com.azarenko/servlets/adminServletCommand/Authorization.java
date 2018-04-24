package com.azarenko.servlets.adminServletCommand;

import com.azarenko.services.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authorization implements Command {
    private ArrayOperation arrayOperation;
    public Authorization(ArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        return this.arrayOperation.authorization(request,resp);
    }
}
