package com.desafio.spring.exceptions;

public class UserNotFoundException extends Exception{
    public String getMessage(){
        return String.format("User not exist");
    }
}
