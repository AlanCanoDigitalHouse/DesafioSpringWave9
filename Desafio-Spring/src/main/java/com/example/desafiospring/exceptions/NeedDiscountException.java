package com.example.desafiospring.exceptions;

public class NeedDiscountException extends Exception{

    public final String ERROR_DISCOUNT = "This product with promo needs a discount";

    public NeedDiscountException() {
        super();
    }
}
