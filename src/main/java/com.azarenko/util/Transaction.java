package com.azarenko.util;

import java.sql.SQLException;

public interface Transaction {

    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

    void reliaseResources() throws TransactionException, SQLException;

}
