package com.azarenko.servlets.adminServletCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.services.TransactionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ManagerCommand implements Command{
    private Command command;

    public ManagerCommand(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
       String f = command.execute(request,resp);
       return f;
    }
}
