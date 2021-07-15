package com.example.desafiospring.exceptions;

public class NotHavePromoProducts extends Exception{

    public final String ERROR_USER = "This user dont have promo products";

    public NotHavePromoProducts() { super();
    }
}
