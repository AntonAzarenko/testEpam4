package com.azarenko.servlets.servletCommands.userCommand;

import com.azarenko.services.ServiceException;
import com.azarenko.servlets.servletCommands.Command;
import com.azarenko.servlets.servletCommands.CommandException;
import com.azarenko.servlets.servletCommands.userOperations.ArrayOperationShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class AddToShoppingCart implements Command {

    private ArrayOperationShoppingCart operationShoppingCart;

    public AddToShoppingCart(ArrayOperationShoppingCart operationShoppingCart) {
        this.operationShoppingCart = operationShoppingCart;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) throws CommandException, ServiceException, UnsupportedEncodingException {
        return this.operationShoppingCart.add(request, resp);
    }
}
