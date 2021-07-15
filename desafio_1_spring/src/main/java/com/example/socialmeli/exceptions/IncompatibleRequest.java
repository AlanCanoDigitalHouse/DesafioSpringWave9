package com.example.socialmeli.exceptions;

public class IncompatibleRequest extends AppException{
    public IncompatibleRequest(){
        message="No puede poner un importe de descuento si el producto no está en promo.";
    }
}
