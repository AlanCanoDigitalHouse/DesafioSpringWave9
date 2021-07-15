package com.example.demo.exceptions;

public class DuplicateUser extends Exception{

    public final String ERROR = "You currently are following this user";

    public DuplicateUser() {
        super();
    }
}
