package com.mercado_libre.bootcamp.desafio2.exceptions;

public class UnexistingNeighborhoodException extends RuntimeException {
    public UnexistingNeighborhoodException(String message) {
        super(message);
    }
}