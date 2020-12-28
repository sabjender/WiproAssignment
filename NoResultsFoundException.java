package com.sab;

public class NoResultsFoundException extends Exception {

    public NoResultsFoundException() {
    }

    public NoResultsFoundException(String errorMessage) {
        super(errorMessage);
    }
}
