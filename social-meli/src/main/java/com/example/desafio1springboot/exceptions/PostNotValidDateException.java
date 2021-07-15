package com.example.desafio1springboot.exceptions;

public class PostNotValidDateException extends Exception{

    public final String ERROR = "The date of the post is after today or invalid";

    public PostNotValidDateException() {
        super();
    }
}
