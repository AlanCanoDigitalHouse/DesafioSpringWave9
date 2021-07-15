package com.socialMeli.exception.exception;

public class OrderNotValidException extends Exception {
    public OrderNotValidException(String invalidOrder) {
        super("Entered a invalid order: " + invalidOrder + " only name_asc and name_desc is valid");
    }

    public OrderNotValidException(String invalidOrder, String valids) {
        super("Entered a invalid order: " + invalidOrder + " only " + valids + " is valid");
    }
}
