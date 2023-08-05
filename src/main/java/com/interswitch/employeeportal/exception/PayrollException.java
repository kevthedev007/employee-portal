package com.interswitch.employeeportal.exception;

import org.springframework.http.HttpStatus;

public class PayrollException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public PayrollException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public PayrollException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
