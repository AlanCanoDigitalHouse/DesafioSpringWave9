package com.example.desafiospring.exceptions;

public class SameUserException extends Exception{
    public final String ERROR_USER = "These two users are the same, you cannot follow";

    public SameUserException() {
        super();
    }
}
