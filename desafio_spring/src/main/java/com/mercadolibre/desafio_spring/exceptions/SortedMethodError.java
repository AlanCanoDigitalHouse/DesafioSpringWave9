package com.mercadolibre.desafio_spring.exceptions;

public class SortedMethodError extends Exception{
    public final String ERROR = "Metodo de ordenado desconocido";
    public SortedMethodError(){
        super();
    }
}
