package com.example.desafio1springboot.exceptions;

public class OrderUserNameNotValidException extends Exception{
    public final String ERROR = "The order is not valid, shoudl be 'name_asc' or 'name_desc'";

    public OrderUserNameNotValidException() {
        super();
    }
}
