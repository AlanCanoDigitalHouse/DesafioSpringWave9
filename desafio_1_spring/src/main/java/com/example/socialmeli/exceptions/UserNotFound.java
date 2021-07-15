package com.example.socialmeli.exceptions;

public class UserNotFound extends AppException{
    public UserNotFound(){
        message = "No se encontró el usuario buscado";
    }
}
