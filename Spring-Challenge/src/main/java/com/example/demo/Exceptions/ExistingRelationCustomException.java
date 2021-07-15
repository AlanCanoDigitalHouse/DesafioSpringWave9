package com.example.demo.Exceptions;

public class ExistingRelationCustomException extends CustomExceptionHandler {

    public ExistingRelationCustomException(){
        super();
        this.ERROR = "La relación entre ambos usuarios ya existe";
    }

}
