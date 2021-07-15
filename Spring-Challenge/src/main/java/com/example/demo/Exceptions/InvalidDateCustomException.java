package com.example.demo.Exceptions;

public class InvalidDateCustomException extends CustomExceptionHandler {

    public InvalidDateCustomException() {
        super();
        this.ERROR = "La fecha ingresada no es válida";
    }

}
