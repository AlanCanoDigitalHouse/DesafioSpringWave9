package com.example.demo.Exceptions;

public class InvalidRelationCustomException extends CustomExceptionHandler {

    public InvalidRelationCustomException() {
        super();
        this.ERROR = "La relación entre los dos usuarios no es válida";
    }

}
