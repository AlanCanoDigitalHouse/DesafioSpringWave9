package com.example.desafiospring.exceptions;

public class HasPromoException extends Exception{

    public final String ERROR_PROMO = "For new post you have to put if the product has promo";

    public HasPromoException() {
        super();
    }
}
