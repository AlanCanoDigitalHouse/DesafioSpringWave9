package com.example.desafiospring.exceptions;

public class UserToFollowAlreadyExistException extends Exception{
    public final String ERROR = "You already follow the user";

    public UserToFollowAlreadyExistException() { super();}
}
