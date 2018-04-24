package com.azarenko.servlets.adminServletCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit implements Command {
    private ArrayOperation arrayOperation;

    public Edit(ArrayOperation arrayOperation) {
        this.arrayOperation = arrayOperation;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        return arrayOperation.edit(request, resp);
    }
}
