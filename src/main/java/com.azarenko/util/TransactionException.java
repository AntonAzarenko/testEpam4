package com.azarenko.util;

import com.azarenko.exceptions.ServiceException;

public class TransactionException extends ServiceException {
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
