package com.example.desafiospring.exceptions;

public class DontNeedDiscountException extends Exception {

    public final String ERROR_PROMO_DISC = "If the product dont have promo, you dont need a discount";

    public DontNeedDiscountException() {
        super();
    }
}
