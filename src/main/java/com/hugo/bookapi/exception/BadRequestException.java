package com.hugo.bookapi.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
