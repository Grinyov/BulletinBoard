package com.grinyov.bulletinboard.exception;

/**
 * @author Vitaliy Grinyov
 * @since 2016.
 */
public class NoSuchAccountException extends RuntimeException {

    public NoSuchAccountException() {
        super();
    }

    public NoSuchAccountException(String message) {
        super(message);
    }

}

