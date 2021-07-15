package com.example.socialmeli.exceptions;

import lombok.Getter;

@Getter
public class AppException extends Exception{
    protected String message;

    public AppException(){
        message="Ocurrió un error inesperado";
    }
}
