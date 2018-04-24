package com.azarenko.servlets.adminServletCommand;

import com.azarenko.services.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class Add implements Command {
    private ArrayOperation arrayOperation;

    public Add(ArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.arrayOperation.add(request,resp);
    }
}
