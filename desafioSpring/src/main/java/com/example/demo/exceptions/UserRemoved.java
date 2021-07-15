package com.example.demo.exceptions;

public class UserRemoved extends Exception{

    public final String ERROR = "User is not following, was removed before";

    public UserRemoved() {
        super();
    }
}
