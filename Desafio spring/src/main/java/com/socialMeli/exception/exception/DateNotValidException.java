package com.socialMeli.exception.exception;

public class DateNotValidException extends Exception {
    public DateNotValidException(String date) {
        super("The date " + date + " not is valid");
    }

    public DateNotValidException(String date, String comment) {
        super("The date " + date + " not is valid, " + comment);
    }
}
