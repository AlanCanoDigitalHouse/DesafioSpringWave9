package com.example.prueba.exceptions;

public class UserSellerNotFoundExceptions extends Exception{

    public final String ERROR = "User seller is not in the database";

    public UserSellerNotFoundExceptions() {
        super();
    }

}
