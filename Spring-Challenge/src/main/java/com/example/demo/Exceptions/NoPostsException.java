package com.example.demo.Exceptions;

public class NoPostsException extends ExceptionHandler {

    public NoPostsException()  {
        super();
        this.ERROR = "No hay publicaciones disponibles";
    }

}
