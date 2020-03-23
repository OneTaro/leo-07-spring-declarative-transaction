package com.spring.declarative.exception;

/**
 * @ClassName RollbackException
 * @Description
 * @Author wangss
 * @date 2020.03.23 23:06
 * @Version 1.0
 */
public class RollbackException extends Exception {
    public RollbackException() {
        super();
    }

    public RollbackException(String message) {
        super(message);
    }

    public RollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollbackException(Throwable cause) {
        super(cause);
    }

    protected RollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
