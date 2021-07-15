package com.example.desafiospring.exceptions;

public class ProductAlreadyExistsException extends Exception{

    public static String ERROR = "Product/Detail already exists or same ID";

    public ProductAlreadyExistsException(){
        super();
    }

}
