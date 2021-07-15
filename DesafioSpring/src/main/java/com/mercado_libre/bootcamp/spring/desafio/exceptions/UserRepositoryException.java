package com.mercado_libre.bootcamp.spring.desafio.exceptions;

public class UserRepositoryException extends RuntimeException {

    public UserRepositoryException(String message) {
        super(message);
    }
}
