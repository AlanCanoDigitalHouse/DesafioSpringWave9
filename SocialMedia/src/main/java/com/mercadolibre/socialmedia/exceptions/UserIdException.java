package com.mercadolibre.socialmedia.exceptions;

import lombok.Data;

@Data
public class UserIdException extends RuntimeException{
    private String msg;
    public UserIdException(String msg){
        super();
        this.msg = msg;
    }
}
