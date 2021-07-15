package com.example.desafiospring.exceptions;

public class PostNotFoundByIdException extends Exception{

    public final String ERROR = "Post Not Found By Id";

    public PostNotFoundByIdException(){
        super();
    }

}
