package com.desafiospring.socialmeli.exceptions;

public class DiscountNullException extends UserException{

    public DiscountNullException() {
        super();
        this.ERROR = "Si esta en promocion debe agregar su descuento.";
    }
}
