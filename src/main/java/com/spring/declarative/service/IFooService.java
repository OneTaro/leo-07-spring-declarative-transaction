package com.spring.declarative.service;

import com.spring.declarative.exception.RollbackException;

public interface IFooService {

    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;

}
