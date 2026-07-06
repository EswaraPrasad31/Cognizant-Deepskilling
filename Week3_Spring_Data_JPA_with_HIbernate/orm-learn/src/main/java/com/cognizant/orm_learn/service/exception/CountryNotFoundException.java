package com.cognizant.orm_learn.service.exception;

public class CountryNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public CountryNotFoundException() {
        super();
    }

    public CountryNotFoundException(String message) {
        super(message);
    }
}
