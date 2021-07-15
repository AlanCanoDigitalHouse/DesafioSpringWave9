package com.example.demo.Exceptions;

public class InvalidRelationException extends ExceptionHandler {

    public InvalidRelationException() {
        super();
        this.ERROR = "La relación entre los dos usuarios no es válida";
    }

}
