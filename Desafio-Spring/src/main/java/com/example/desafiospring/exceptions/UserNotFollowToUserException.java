package com.example.desafiospring.exceptions;

public class UserNotFollowToUserException extends Exception{
    public final String ERROR = "You don't follow the user";

    public UserNotFollowToUserException(){super();}
}
