package com.example.demo.Exceptions;

public class NoPostsCustomException extends CustomExceptionHandler {

    public NoPostsCustomException()  {
        super();
        this.ERROR = "No hay publicaciones disponibles";
    }

}
