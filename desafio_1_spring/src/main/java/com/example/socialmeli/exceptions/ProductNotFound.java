package com.example.socialmeli.exceptions;

public class ProductNotFound extends AppException {
    public ProductNotFound(){
        message = "No se encontró el tipo de ordenamiento deseado";
    }
}
