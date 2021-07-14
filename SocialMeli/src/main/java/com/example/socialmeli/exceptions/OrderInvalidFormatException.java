package com.example.socialmeli.exceptions;

public class OrderInvalidFormatException extends Exception {

    public static final String ERROR_NAME = "Parametro invalido para el ordenamiento. Valores validos: name_asc o name_desc";

    public static final String ERROR_DATE = "Parametro invalido para el ordenamiento. Valores validos: date_asc o date_desc";

    public OrderInvalidFormatException(String message) {
        super(message);
    }
}
