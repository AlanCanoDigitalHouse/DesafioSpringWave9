package com.example.desafiospring.exceptions;

public class UserNotFindException extends Exception{

    public final String ERROR = "Un usuario no existe";

    public UserNotFindException(){
        super();
    }
}
