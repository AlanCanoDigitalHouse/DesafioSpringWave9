package com.mercadolibre.desafio.exception;

public class UserException extends Exception {
    public static  final  String  ID_NOT_FOUND = "user id not found";

    public UserException(){
        super();
    }
    public UserException(String msg){
        super(msg);
    }
}
