package com.azarenko.servlets.servletCommands.adminCommand;

import com.azarenko.services.TransactionException;
import com.azarenko.servlets.servletCommands.adminOperations.ArrayOperationSubscriptions;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSubscription implements Command {
    public ShowSubscription(ArrayOperationSubscriptions arrayOperationSubscriptions) {
        this.arrayOperationSubscriptions = arrayOperationSubscriptions;
    }

    private ArrayOperationSubscriptions arrayOperationSubscriptions;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, TransactionException {
        return this.arrayOperationSubscriptions.showSubscription(request, resp);
    }
}
