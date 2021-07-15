package com.example.demo.exceptions;

public class UserNotFound extends Exception{

    public final String ERROR = "User wasn't found in our database";

    public UserNotFound() {
        super();
    }
}
