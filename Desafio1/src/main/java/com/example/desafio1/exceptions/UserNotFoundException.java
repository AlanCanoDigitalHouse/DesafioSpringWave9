package com.example.desafio1.exceptions;

public class UserNotFoundException extends Exception{
    private Integer userId;

    public String getMessage(){
        return String.format("User with Id %d does not exist", this.userId);
    }
}
