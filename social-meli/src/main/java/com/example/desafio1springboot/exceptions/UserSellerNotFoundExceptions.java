package com.example.desafio1springboot.exceptions;

public class UserSellerNotFoundExceptions extends Exception{

    public final String ERROR = "User seller is not in the database";

    public UserSellerNotFoundExceptions() {
        super();
    }

}
