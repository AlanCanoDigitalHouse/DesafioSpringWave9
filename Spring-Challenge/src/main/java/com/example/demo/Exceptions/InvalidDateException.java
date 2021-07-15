package com.example.demo.Exceptions;

public class InvalidDateException extends ExceptionHandler {

    public InvalidDateException() {
        super();
        this.ERROR = "La fecha ingresada no es válida";
    }

}
