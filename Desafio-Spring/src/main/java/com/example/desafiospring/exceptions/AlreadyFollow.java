package com.example.desafiospring.exceptions;

public class AlreadyFollow extends Exception {

    public final String ERROR_FOLLOW = "You already follow this user, try with another user";

    public AlreadyFollow() {
        super();
    }
}
