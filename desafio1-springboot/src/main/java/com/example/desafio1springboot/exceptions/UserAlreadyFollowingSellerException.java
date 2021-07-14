package com.example.desafio1springboot.exceptions;

public class UserAlreadyFollowingSellerException extends Exception{

    public final String ERROR = "User is already following the seller";

    public UserAlreadyFollowingSellerException() {
        super();
    }
}
