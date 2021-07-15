package com.example.desafiospring.exceptions;

public class UserNotFindOrEqualException extends Exception{

    public final String ERROR = "User not found or both users are equals userId";

    public UserNotFindOrEqualException(){
        super();
    }
}
