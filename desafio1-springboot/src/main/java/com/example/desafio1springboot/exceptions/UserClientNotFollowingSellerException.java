package com.example.desafio1springboot.exceptions;

public class UserClientNotFollowingSellerException extends Exception{

    public final String ERROR = "User is not following the seller";

    public UserClientNotFollowingSellerException() {
        super();
    }
}
