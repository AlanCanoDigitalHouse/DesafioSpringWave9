package com.example.desafiospring.exceptions;

public class OrderNotExistsException extends Exception{

    public final String ERROR = "Order not exists";

    public OrderNotExistsException(){ super();}

}
