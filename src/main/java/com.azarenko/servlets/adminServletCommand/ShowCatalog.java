package com.azarenko.servlets.adminServletCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.services.TransactionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCatalog implements Command {
    private ArrayOperation arrayOperation;

    public ShowCatalog(ArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException {
        String f = this.arrayOperation.showCatalog(request, resp);
        return f;
    }
}
