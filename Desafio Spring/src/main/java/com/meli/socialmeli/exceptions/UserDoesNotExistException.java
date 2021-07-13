package com.meli.socialmeli.exceptions;

public class UserDoesNotExistException extends Exception{
    public String ERROR;
    public UserDoesNotExistException(String user){
        super();
        this.ERROR = "User " + user + " does not exists.";
    }
}
