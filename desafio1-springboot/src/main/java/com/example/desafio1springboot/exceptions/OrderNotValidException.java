package com.example.desafio1springboot.exceptions;

public class OrderNotValidException extends Exception{

    public final String ERROR = "The order is not valid";

    public OrderNotValidException() {
        super();
    }
}
