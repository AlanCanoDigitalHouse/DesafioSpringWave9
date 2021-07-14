package com.example.desafiospring.exceptions;

public class UserNotFollowToUser extends Exception{
    public final String ERROR = "El usuario no sigue al usuario el cual quieres dejar de seguir.";

    public UserNotFollowToUser(){super();}
}
