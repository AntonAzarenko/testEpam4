package com.azarenko.util;

import com.azarenko.services.ServiceException;

public class TransactionException extends ServiceException {
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
