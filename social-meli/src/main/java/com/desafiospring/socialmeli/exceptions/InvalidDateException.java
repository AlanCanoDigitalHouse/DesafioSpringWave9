package com.desafiospring.socialmeli.exceptions;


public class InvalidDateException extends UserException {

    public InvalidDateException(){
        super();
        this.ERROR = "Fecha invalida";
    }
}
