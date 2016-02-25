package com.grinyov.bulletinboard.exception;

/**
 * @author Vitaliy Grinyov
 * @since 2016.
 */
public class NoSuchAdvertException extends RuntimeException {

    public NoSuchAdvertException() {
        super();
    }

    public NoSuchAdvertException(String message) {
        super(message);
    }

}