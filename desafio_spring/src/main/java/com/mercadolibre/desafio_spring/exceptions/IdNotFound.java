package com.mercadolibre.desafio_spring.exceptions;

public class IdNotFound extends Exception{
    public final String ERROR ="El id no se encuentra o esta errado";
    public IdNotFound() {super();}
}
