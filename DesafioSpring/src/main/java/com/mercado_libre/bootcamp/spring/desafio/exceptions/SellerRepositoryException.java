package com.mercado_libre.bootcamp.spring.desafio.exceptions;

public class SellerRepositoryException extends RuntimeException {

    public SellerRepositoryException(String message) {
        super(message);
    }
}
