package com.mercadolibre.socialmedia.exceptions;

import lombok.Data;

@Data
public class ParseDateException extends RuntimeException{
    private String msg;
    public ParseDateException(String msg){
        super();
        this.msg = msg;
    }
}
