package com.hugo.bookapi.exception;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
