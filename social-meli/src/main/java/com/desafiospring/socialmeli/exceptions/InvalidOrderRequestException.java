package com.desafiospring.socialmeli.exceptions;

public class InvalidOrderRequestException extends UserException{

    public InvalidOrderRequestException() {
        super();
        this.ERROR = "Orden invalido";
    }
}
